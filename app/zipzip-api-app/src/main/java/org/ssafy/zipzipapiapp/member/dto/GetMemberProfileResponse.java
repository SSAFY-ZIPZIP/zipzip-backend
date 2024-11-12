package org.ssafy.zipzipapiapp.member.dto;


public record GetMemberProfileResponse(
        String email,
        String nickname,
        String profileImgUrl
) {
}
