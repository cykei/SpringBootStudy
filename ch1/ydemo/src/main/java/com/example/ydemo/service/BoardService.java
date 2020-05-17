package com.example.ydemo.service;

import com.example.ydemo.domain.Board;
import com.example.ydemo.repository.BoardRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/* 비즈니스 로직 */

@Service
@Transactional
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public Board createPost(Board board){
        board.setReg_dt(LocalDateTime.now());
        return boardRepository.save(board);
    }

    public Board readPost(int bno){
        Board board = boardRepository.getOne(bno);
        return board;
    }

    public List<Board> readAllPost(){
        List<Board> boards =  boardRepository.findAll();
        return boards;
    }

    public Board updatePost(int bno, Board board) {
        /*
        Board old = boardRepository.getOne(bno);

        old.setBno(bno);
        old.setReg_dt(board.getReg_dt());
        old.setUserID(board.getUserID());
        old.setTitle(board.getTitle());
        old.setContents(board.getContents());
        return boardRepository.save(old);

         */
        Board old = boardRepository.getOne(bno);
        System.out.println("fkfkfkfkfkf" + old.getBno());

        old.setTitle(board.getTitle());
        old.setContents(board.getContents());
        return boardRepository.save(old);
    }

    public void deletePost(int bno){
        boardRepository.deleteById(bno);
    }
}
