package org.ssafy.zipzipapiapp.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.member.dto.GetMemberProfileResponse;
import org.ssafy.zipzipexceptioncommon.exception.ErrorMessage;
import org.ssafy.zipzipexceptioncommon.exception.NotFoundException;
import org.ssafy.zipzipmysqldomain.member.entity.Member;
import org.ssafy.zipzipmysqldomain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberSerivce {
    private final MemberRepository memberRepository;

    public GetMemberProfileResponse getMemberProfile(Long memberId) {
        Member findMember = memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ERR_NOT_FOUND_MEMBER));

        return new GetMemberProfileResponse(findMember.getEmail(), findMember.getNickname(),
                findMember.getProfileImageUrl());
    }
}
