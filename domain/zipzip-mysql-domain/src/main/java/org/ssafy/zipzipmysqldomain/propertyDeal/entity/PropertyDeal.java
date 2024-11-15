package org.ssafy.zipzipmysqldomain.propertyDeal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.zipzipmysqldomain.common.entity.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PropertyDeal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String aptSeq;

    @Column(length = 40)
    private String aptDong;

    @Column(length = 10)
    private String dealAmount;

    @Column
    private Integer dealYear;

    @Column
    private Integer dealMonth;

    @Column
    private Integer dealDay;

    @Column(precision = 7, scale = 2)
    private BigDecimal excluUseAr;

    @Column(length = 3)
    private String floor;

    @Builder
    public PropertyDeal(String aptSeq, String aptDong, String dealAmount, Integer dealYear, Integer dealMonth,
                        Integer dealDay, BigDecimal excluUseAr, String floor) {
        this.aptSeq = aptSeq;
        this.aptDong = aptDong;
        this.dealAmount = dealAmount;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
        this.excluUseAr = excluUseAr;
        this.floor = floor;
    }
}
