package org.ssafy.zipzipapiapp.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.member.dto.GetMemberProfileResponse;
import org.ssafy.zipzipapiapp.member.service.MemberSerivce;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {
    private final MemberSerivce memberSerivce;

    @GetMapping("/{memberId}/profile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetMemberProfileResponse> getMemberProfile(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(memberSerivce.getMemberProfile(memberId));
    }
}
