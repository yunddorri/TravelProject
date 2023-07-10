package com.example.travel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {
    // 최상위 루트 -> 메인 페이지
    @GetMapping(value="/")
    public String main() {
        return "main";
    }

}
