package kr.oshino.eataku.member.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminViewController {

    /**
     * 관리자 회원가입
     */
    @GetMapping("/signUp")
    public String managerSignUpProc() {
        return "admin/signUp";

    }
}
