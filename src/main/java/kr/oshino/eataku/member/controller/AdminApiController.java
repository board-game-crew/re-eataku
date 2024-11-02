package kr.oshino.eataku.member.controller;

import jakarta.servlet.http.HttpSession;
import kr.oshino.eataku.restaurant.model.dto.RestaurantAccountInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminApiController {

    /**
     * 관리자 회원가입
     */
    @PostMapping("/signUp")
    public ResponseEntity<String> managerSignUpProc(@RequestBody RestaurantAccountInfoDTO accountInfo, HttpSession session) {

        log.info("⭐️⭐️ [ MemberController ] SignUp Restaurant : {} ⭐️⭐️", accountInfo);

        session.setAttribute("id", accountInfo.getId());
        session.setAttribute("password", accountInfo.getPassword());
        session.setAttribute("email", accountInfo.getEmail());

        return ResponseEntity.ok("/restaurant/certification");
    }
}
