package com.example.demo.board.controller;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardMapper boardMapper;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    /* 데이터 반환 */
    /*
    @RequestMapping("/board")
    public List<BoardVO> board() throws Exception{
        List<BoardVO> board = boardMapper.boardList();
        return board;
    }
    */

    /* 데이터를 jsp에 반환 */
    @RequestMapping("/board")
    public ModelAndView board() throws Exception{
        List<BoardVO> board = boardMapper.boardList();
        return new ModelAndView("boardList","list",board);
        //boardList.jsp의 list변수에다가 board객체를 반환한다.
    }

    //게시글 작성 페이지(GET)
    @GetMapping("/board/post")
    public ModelAndView writeForm() throws Exception{

        return new ModelAndView("boardWrite");
    }

    //게시글 작성(POST)
    @PostMapping("/board/post")
    public String write(@ModelAttribute("BoardVO") BoardVO board) throws Exception{

        boardMapper.boardInsert(board);
        return "redirect://localhost:8080/board";

    }
}