package org.ssafy.zipzipmysqldomain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.zipzipmysqldomain.common.entity.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String socialId;

    @Column
    private String profileImageUrl;

    @Column
    private String refreshToken;

    @Builder
    public Member(String nickname, String email, String socialId, String profileImageUrl) {
        this.nickname = nickname;
        this.email = email;
        this.socialId = socialId;
        this.profileImageUrl = profileImageUrl;
    }
}
