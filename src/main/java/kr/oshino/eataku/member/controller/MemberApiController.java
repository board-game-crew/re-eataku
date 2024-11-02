package kr.oshino.eataku.member.controller;

import kr.oshino.eataku.member.model.dto.CustomMemberDetails;
import kr.oshino.eataku.member.model.dto.MemberDTO;
import kr.oshino.eataku.member.service.MailService;
import kr.oshino.eataku.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MailService mailService;

    /**
     * ID 중복체크 API
     */
    @GetMapping("/check/id")
    public ResponseEntity<Boolean> checkDuplicateAccount(@RequestParam String id) {

        log.info("⭐️⭐️ [ MemberController ] Check Duplicate account : {} ⭐️⭐️", id);
        boolean isDuplicate = memberService.checkDuplicateAccount(id);
        log.info("⭐️⭐️ [ MemberController ] Check Duplicate account : {} ⭐️⭐️", isDuplicate);

        return ResponseEntity.ok(isDuplicate);
    }

    /**
     * 닉네임 중복체크 API
     */
    @GetMapping("/check/name")
    public ResponseEntity<Boolean> checkDuplicateNickname(@RequestParam String username) {

        log.info("⭐️⭐️ [ MemberController ] Check Duplicate nickname : {} ⭐️⭐️", username);
        boolean isDuplicate = memberService.checkDuplicateNickname(username);
        log.info("⭐️⭐️ [ MemberController ] Check Duplicate nickname : {} ⭐️⭐️", isDuplicate);

        return ResponseEntity.ok(isDuplicate);
    }

    /**
     * 이메일 인증코드 전송 API
     */
    @PostMapping("/check/email")
    public ResponseEntity<Boolean> sendEmailVerifCode(@RequestParam String email) {

        log.info("⭐️⭐️ [ MemberController ] Send to Email : {} ⭐️⭐️", email);
        int verifCode = mailService.sendEmailVerifCode(email);
        log.info("⭐️⭐️ [ MemberController ] Email Send verifCode T/F : {} ⭐️⭐️", verifCode);

        return ResponseEntity.ok(true);
    }

    /**
     * 이메일 인증코드 확인 API
     */
    @GetMapping("/check/email")
    public ResponseEntity<Boolean> checkEmailVerifCode(@RequestParam String email, @RequestParam String token) {

        log.info("⭐️⭐️ [ MemberController ] Request Email : {}, VerifCode : {} ⭐️⭐️", email, token);
        boolean isMatch = mailService.checkMailVerifCode(email, token);

        return ResponseEntity.ok(isMatch);
    }

    /**
     * 일반회원가입 API
     */
    @PostMapping("/signUp")
    public ResponseEntity<String> signUpProc(@RequestBody MemberDTO member) {

        log.info("⭐️⭐️ [ MemberController ] SignUp MemberInfo : {} ⭐️⭐️", member);
        memberService.insertNewMember(member);

        return ResponseEntity.ok("/login");
    }

    /**
     * 내 정보 수정 API
     */
    @PutMapping("/myInfo")
    public ResponseEntity<Boolean> modifyProfile(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("jsonData") MemberDTO member){

        // 현재 로그인 정보 불러오기
        CustomMemberDetails logginedMember = (CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long logginedMemberNo = logginedMember.getMemberNo();

        log.info("⭐️⭐️ [ MemberController ] modifyProfile file : {}, member : {} ⭐️⭐️", file.getName() , member);
        boolean isSuccess = memberService.updateProfile(file, member, logginedMemberNo);

        return ResponseEntity.ok(isSuccess);
    }

    /**
     * ID 찾기 API
     */
    @PutMapping("/find/id")
    public ResponseEntity<Boolean> findIdProc(@RequestParam String name,@RequestParam String email){

        log.info("⭐️⭐️ [ MemberController ] find id By name : {}, email : {} ⭐️⭐️", name , email);
        boolean isSuccess = false;

        if ( name != null && !name.isEmpty()  && email != null && !email.isEmpty() ){
            isSuccess = memberService.findAccountByNameAndEmail(name,email);
        }

        if(isSuccess){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().body(false);
    }

    /**
     * 비밀번호 찾기 API
     */
    @PutMapping("/find/pw")
    public ResponseEntity<Boolean> findPwProc(@RequestParam String id, @RequestParam String name,@RequestParam String email){

        log.info("⭐️⭐️ [ MemberController ] find pw By id : {}, name : {}, email : {} ⭐️⭐️", id, name , email);
        boolean isSuccess = false;

        if ( id != null && !id.isEmpty() && name != null && !name.isEmpty()  && email != null && !email.isEmpty() ){
            isSuccess = memberService.findPasswordByIdAndNameAndEmail(id,name,email);
        }

        if(isSuccess){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().body(false);
    }
}
