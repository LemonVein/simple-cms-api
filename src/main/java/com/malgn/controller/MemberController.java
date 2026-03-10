package com.malgn.controller;

import com.malgn.domain.Member;
import com.malgn.dto.member.loginRequestDto;
import com.malgn.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody loginRequestDto loginRequestDto) {
        String token = memberService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
