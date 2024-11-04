package org.ssafy.zipzipappapi.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

    HttpStatus status;
    ErrorMessage errorMessage;

    public BaseException(HttpStatus status, ErrorMessage errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
