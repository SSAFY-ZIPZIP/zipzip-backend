package org.ssafy.zipzipmysqldomain.dongCode.entity;

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
public class DongCode extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String dongCode;

    @Column(length = 30)
    private String sidoName;

    @Column(length = 30)
    private String gugunName;

    @Column(length = 30)
    private String dongName;

    @Builder
    public DongCode(String dongCode, String sidoName, String gugunName, String dongName) {
        this.dongCode = dongCode;
        this.sidoName = sidoName;
        this.gugunName = gugunName;
        this.dongName = dongName;
    }

}
