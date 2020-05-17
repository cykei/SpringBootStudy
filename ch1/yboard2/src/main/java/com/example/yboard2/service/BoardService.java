package com.example.yboard2.service;

import com.example.yboard2.domain.entity.BoardEntity;
import com.example.yboard2.domain.repository.BoardRepository;
import com.example.yboard2.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor  // 이걸 쓰면 repository에 @Autowired를 안써도 된다.
@Service
public class BoardService {
    private BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    @Transactional
    public List<BoardDto> getBoardList(Integer pageNum) {
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        // 글쓰기 "등록" 을 눌렀는데 브라우저가 멈춰서 한번더 등록을 누르게 되면
        // 게시글이 두번들어가는 오류가 있다.
        // 이 문제를 어떻게 해결하는게 좋을까?

        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        // 이런식으로 Optional 클래스를 써서 얻는 메리트가 뭐가 있을까...?

        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
        // 정년 일걸 이렇게 일일히 해줘야 한단 말인가.
        // 이게 최선인것인가!!

        return boardDto;


    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        // save() : JpaRepository에서 정의된 메서드
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    // 검색 ////////////////////////////////////////////////////////
    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        //List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardEntity> boardEntities = boardRepository.findByContentContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;
        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }

    // 페이징 ///////////////////////////////////////////////

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산(올림)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }


}