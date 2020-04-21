package com.example.demo;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.mapper.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.board.mapper.BoardMapper;
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private BoardMapper mapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testMapper() throws Exception{//작성

        BoardVO vo = new BoardVO();

        vo.setTitle("제목입니다.");
        vo.setContents("내용입니다.");
        vo.setUserID("작성자입니다.");

        mapper.boardInsert(vo);

    }


}
