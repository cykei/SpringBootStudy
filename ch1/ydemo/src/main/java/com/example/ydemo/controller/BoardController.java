package com.example.ydemo.controller;



import com.example.ydemo.repository.BoardRepository;
import com.example.ydemo.domain.Board;
import com.example.ydemo.service.BoardService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.List;


@Controller
@EntityScan("com.example.ydemo.domain")
@EnableJpaRepositories("com.example.ydemo.repository")
public class BoardController {

    //@Autowired
    //BoardRepository boardRepository;
    @Autowired
    BoardService service;

    @GetMapping("/board")
    public String getBoardList(Model model){
        //List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list", service.readAllPost());
        return "board";
    }

    @GetMapping("/board/create")
    public String getBoardInsert(Model model){
        return "boardForm";
    }

    @PostMapping("/board/create")
    public String postBoardInsert(Board board, Model model){

        service.createPost(board);
        model.addAttribute("list", service.readAllPost());
        return "redirect:/board";
    }

    @GetMapping("/board/update/{bno}")
    public String getSelectOne(Model model, @PathVariable int bno){
        Board board = service.readPost(bno);
        model.addAttribute("updateValue",board);
        return "boardUpdate";
    }

    @PostMapping("/board/update")
    public String postBoardUpdate(Board board, Model model){
        service.updatePost(board.getBno(), board);
        model.addAttribute("list", service.readAllPost());
        return "redirect:/board";
    }


    @GetMapping("/board/delete")
    public String getBoardDelete(Board board, Model model){
        service.deletePost(board.getBno());
        model.addAttribute("list", service);
        return "redirect:/board";

    }

    // 위와 아래는 내부적으로 완전히 동일한 방식이다.
    // 아래가 더 오래된 방식이고 위에것이 새방식
    // 위와 아래의 다른 점은 위의 것은 redirect: 가 붙어있어서 삭제후 url이 /board이다.
    // 아래것은 삭제후 url이 /board/delete 이다.
    // 위의 것에서 return "/board" 라고 쓴다면 삭제후 /board/delete로 아래것과 동일하다.

/*
    @GetMapping("/board2/delete")
    public ModelAndView getBoardDelete2(Board board){
        ModelAndView model = new ModelAndView("board");
        boardRepository.deleteById(board.getBno());
        model.addObject("list", boardRepository.findAll());
        return model;
    }

    @GetMapping("/board2")
    public ModelAndView getBoardList2(){
        ModelAndView model = new ModelAndView("board");
        // list : html 던져줄때 사용해야 되는 객체 명
        // findAll() : jpaRepository에서 제공하는 인터페이스.
        //             mybatis 같은 경우 내가 쿼리문을 직접 입력해야하는데
        //             이거 같은 경우 그냥 이 함수를 쓰는 것 만으로도 hibernate를 조회해서 데이터를 가져온다.
        //             즉, 이 findAll() 안에 쿼리문이 다 내장되어 있는 듯
        //             요건 이름에서 느껴지듯이 전체 조회기능이다.
        model.addObject("list", boardRepository.findAll());
        return model;
    }

    @PostMapping("/board2/create")
    public ModelAndView postBoardInsert2(Board board){
        ModelAndView model = new ModelAndView("board");

        boardRepository.save(board);
        model.addObject("list", boardRepository.findAll());
        return model;
    }

    @GetMapping("/board2/selectOne")
    public ModelAndView getSelectOne2(Board board){
        ModelAndView model = new ModelAndView("boardUpdate");
        model.addObject("updateValue",boardRepository.getOne(board.getBno()));
        return model;
    }
*/
}
