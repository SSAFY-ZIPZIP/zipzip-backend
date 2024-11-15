package org.ssafy.zipzipmysqldomain.propertyInfo.entity;

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
public class PropertyInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String aptNm;

    @Column(length = 20)
    private String aptSeq;

    @Column
    private Integer buildYear;

    @Column(length = 10)
    private String jibun;

    @Column(length = 45)
    private String latitude;

    @Column(length = 45)
    private String longitude;

    @Column(length = 20)
    private String roadNm;

    @Column(length = 5)
    private String roadNmSggCd;

    @Column(length = 10)
    private String roadNmBonbun;

    @Column(length = 10)
    private String roadNmBubun;

    @Column(length = 20)
    private String umdNm;

    @Column(length = 5)
    private String sggCd;

    @Column(length = 5)
    private String umdCd;

    @Builder
    public PropertyInfo(String aptNm, String aptSeq, Integer buildYear, String jibun, String latitude,
                        String longitude, String roadNm, String roadNmSggCd, String roadNmBonbun, String roadNmBubun,
                        String umdNm, String sggCd, String umdCd) {
        this.aptNm = aptNm;
        this.aptSeq = aptSeq;
        this.buildYear = buildYear;
        this.jibun = jibun;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roadNm = roadNm;
        this.roadNmSggCd = roadNmSggCd;
        this.roadNmBonbun = roadNmBonbun;
        this.roadNmBubun = roadNmBubun;
        this.umdNm = umdNm;
        this.sggCd = sggCd;
        this.umdCd = umdCd;
    }
}
