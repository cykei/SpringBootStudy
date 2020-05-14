package com.example.yboard2.service;

import com.example.yboard2.domain.entity.BoardEntity;
import com.example.yboard2.domain.repository.BoardRepository;
import com.example.yboard2.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor  // 이걸 쓰면 repository에 @Autowired를 안써도 된다.
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getBoardList(){;
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities){
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id){
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
    public Long savePost(BoardDto boardDto){
        // save() : JpaRepository에서 정의된 메서드
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }

}
