package com.example.demo.board.mapper;

import com.example.demo.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    public void boardInsert(BoardVO board)throws Exception;
    public List<BoardVO> boardList()throws Exception;

}

