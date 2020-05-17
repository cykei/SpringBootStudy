package com.example.yboard2.domain.repository;

import com.example.yboard2.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
    List<BoardEntity> findByContentContaining(String keyword);

}
