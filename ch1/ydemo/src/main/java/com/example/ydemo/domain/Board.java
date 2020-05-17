package com.example.ydemo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name="board")  //db안에 테이블이 여러개면 이름을 지정해줘야 한다.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bno")
    int bno;

    @Column(name="title")
    String title;
    @Column(name="userID")
    String userID;
    @Column(name="contents")
    String contents;

    @Column(name="reg_dt")
    LocalDateTime reg_dt;

    // 하이버네이트 5.2이상에서만 돌아가는 어노테이션 @CreationTiemstamp
    // 이걸 붙인 상태에서 update 처리를 어떻게 해야하는지 모르겠음... ㅠ
    /*
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-DD'T'HH:mm:ss") //직렬화?
    @Column(name="reg_dt")
    LocalDateTime reg_dt;
    */
}
