package org.ssafy.zipzipapiapp.common.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public record PageMetaDto(
        @NotNull Integer itemCount,  // 응답 데이터 수
        @NotNull Integer pageCount,  // 총 페이지 수
        @NotNull boolean hasPreviousPage,  // 이전 페이지 유무
        @NotNull boolean hasNextPage  // 다음 페이지 유무
) {
    public PageMetaDto(Page<?> page) {
        this(
                (int) page.getTotalElements(),
                page.getTotalPages(),
                page.hasPrevious(),
                page.hasNext()
        );
    }
}