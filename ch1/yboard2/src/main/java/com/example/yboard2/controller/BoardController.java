package com.example.yboard2.controller;

import com.example.yboard2.dto.BoardDto;
import com.example.yboard2.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor // 이걸쓰면 service에 @Autowired를 안써도 된다.
public class BoardController {
    private BoardService boardService;
    /*
    문득 드는 의문
    1. BoardService를 전역으로 선언한다는 것은 이 BoardController라는 객체를 자주 쓰기 때문에
    한번 선언해서 여러함수에 쓰는 편리함을 누리겠다는 의미인듯
    그렇다면, BoardController는 싱글톤인가?
    2. BoardController 가 싱글톤이 아니라면 new BoardController를 단 한번만 실행한다는 의미인건가?

     */
    @GetMapping("/")
    public String list(Model model){
        // RestController가 아니기 때문에 반환값으로는 view 또는 redirect가 와야한다.

        List<BoardDto> boardList = boardService.getBoardList();

        model.addAttribute("boardList",boardList);
        return "board/list";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto",boardDto);
        return "board/detail";
    }


    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);
        model.addAttribute("boardDto",boardDto);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no){
        boardService.deletePost(no);
        return "redirect:/";
    }

    //검색
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList",boardDtoList);
        return "board/list";
    }
}
