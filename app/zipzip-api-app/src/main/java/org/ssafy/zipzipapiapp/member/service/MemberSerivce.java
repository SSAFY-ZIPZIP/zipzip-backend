package org.ssafy.zipzipapiapp.member.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_NOT_FOUND_MEMBER;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.member.dto.GetMemberProfileResponse;
import org.ssafy.zipzipexceptioncommon.exception.NotFoundException;
import org.ssafy.zipzipmysqldomain.member.entity.Member;
import org.ssafy.zipzipmysqldomain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberSerivce {
    private final MemberRepository memberRepository;

    public GetMemberProfileResponse getMemberProfile(Long memberId) {
        Member findMember = findByIdOrThrow(memberId);
        return new GetMemberProfileResponse(findMember.getEmail(), findMember.getNickname(),
                findMember.getProfileImageUrl());
    }

    public Long findMemberIdByEmailOrThrow(String email) {
        return memberRepository.findMemberIdByEmail(email)
                .orElseThrow(() -> new NotFoundException(ERR_NOT_FOUND_MEMBER));
    }

    public Member findByIdOrThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ERR_NOT_FOUND_MEMBER));
    }

}
