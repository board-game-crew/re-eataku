package kr.oshino.eataku.member.controller;

import kr.oshino.eataku.member.model.dto.CustomMemberDetails;
import kr.oshino.eataku.member.model.dto.HistoryDTO;
import kr.oshino.eataku.member.model.dto.MemberProfileDTO;
import kr.oshino.eataku.member.model.dto.MyInfoDTO;
import kr.oshino.eataku.member.model.entity.Member;
import kr.oshino.eataku.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberViewController {

    // 카카오ID 및 URI
    @Value("${kakao.client_id}")
    private String kakaoClientId;
    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    // 호출 서비스
    private final MemberService memberService;

    /**
     * 일반회원 로그인
     */
    @GetMapping("/login")
    public String login(Model model) {

        // 카카오 로그인 버튼 주소 할당
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+kakaoClientId+"&redirect_uri="+kakaoRedirectUri;
        model.addAttribute("location", location);

        return "member/login";
    }

    /**
     * 일반회원가입
     */
    @GetMapping("/signUp")
    public String signUpPage() {
        return "member/signUp";
    }

    /**
     * 다른 사용자 정보페이지
     */
    @GetMapping("/info/{memberNo}")
    public String memberInfo(@PathVariable("memberNo") Long memberNo, Model model) {

        // 프로필 정보 조회
        MemberProfileDTO member = memberService.selectProfile(memberNo);
        model.addAttribute("member", member);

        return "member/memberProfile";
    }

    /**
     * 마이페이지
     */
    @GetMapping("/myPage")
    public String myPage(Model model) {

        // 로그인 정보 불러오기
        CustomMemberDetails logginedMember = (CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long logginedMemberNo = logginedMember.getMemberNo();

        // 프로필 정보 조회
        MyInfoDTO member = memberService.selectMyProfile(logginedMemberNo);

        model.addAttribute("member", member);

        return "member/myPage";
    }

    /**
     * 내 정보 수정
     */
    @GetMapping("/myInfo")
    public String myInfo(Model model) {

        // 로그인 정보 불러오기
        CustomMemberDetails logginedMember = (CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long logginedMemberNo = logginedMember.getMemberNo();

        // 프로필 정보 조회
        Member member = memberService.selectMyData(logginedMemberNo);

        model.addAttribute("member", member);

        return "member/editProfile";
    }

    /**
     * 찾기 페이지
     */
    @GetMapping("/find")
    public String find() {
        return "member/find";
    }

    /**
     * 아이디 찾기
     */
    @GetMapping("/find/id")
    public String findId() {
        return "member/findId";
    }

    /**
     * 비밀번호 찾기
     */
    @GetMapping("/find/pw")
    public String findPw() {
        return "member/findPw";
    }

    /**
     * 이용내역
     */
    @GetMapping("/history")
    public String history(Model model) {

        // 로그인 정보 가져오기
        CustomMemberDetails logginedMember = (CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long logginedMemberNo = logginedMember.getMemberNo();

        // 이용내역 조회
        List<HistoryDTO> historyList = memberService.selectHistory(logginedMemberNo);

        model.addAttribute("historyList", historyList);

        return "member/history";
    }
}
