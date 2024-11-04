package org.ssafy.zipzipappapi.common.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ssafy.zipzipappapi.common.exception.BaseException;
import org.ssafy.zipzipappapi.common.exception.FailResponse;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<FailResponse> handleGlobalException(BaseException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(FailResponse.fail(ex.getStatus().value(), ex.getErrorMessage()));
    }
}