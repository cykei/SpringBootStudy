package com.example.ydemo.repository;

import com.example.ydemo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

//crud 쓰기위한 인터페이스. 상속받으면 자동으로 CRUD기능을 사용할 수 있다.
public interface BoardRepository extends JpaRepository<Board, Integer> {
    //왜 Integer인가? Board의 pk인 bno가 int이기 때문이지

}
