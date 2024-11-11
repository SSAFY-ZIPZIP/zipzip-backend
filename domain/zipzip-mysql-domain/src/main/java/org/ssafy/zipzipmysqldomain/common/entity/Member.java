package org.ssafy.zipzipmysqldomain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column(nullable = false, name = "social_id")
    private String socialId;

    @Column
    private String profileImage;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public Member(String nickname, String email, String socialId, String profileImage) {
        this.nickname = nickname;
        this.email = email;
        this.socialId = socialId;
        this.profileImage = profileImage;
    }
}
