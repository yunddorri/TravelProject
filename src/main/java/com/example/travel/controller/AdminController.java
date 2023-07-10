package com.example.travel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// admin 기능이 필요한지는 잘 모르겠음, 나중에 삭제
@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {
    @GetMapping(value="")
    public String admin() {
        return "admin/adminPage";
    }

}
