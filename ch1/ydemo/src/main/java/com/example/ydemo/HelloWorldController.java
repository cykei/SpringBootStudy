package com.example.ydemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(){
        return "hello"; //이거 파일명 입니다.
    }

}
