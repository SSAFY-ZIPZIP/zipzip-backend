package org.ssafy.zipzipapiapp.common.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ssafy.zipzipexceptioncommon.exception.BaseException;
import org.ssafy.zipzipexceptioncommon.exception.FailResponse;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<FailResponse> handleGlobalException(BaseException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(FailResponse.fail(ex.getStatus().value(), ex.getErrorMessage().toString()));
    }
}