package org.ssafy.zipzipapiapp.common.advice;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_INVALID_REQUEST_BODY_FORMAT;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ssafy.zipzipexceptioncommon.exception.BaseException;
import org.ssafy.zipzipexceptioncommon.exception.FailResponse;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<FailResponse> handleGlobalException(BaseException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(FailResponse.fail(ex.getStatus().value(), ex.getErrorMessage().toString()));
    }

    // @Valid로 검증하는 필드에 대한 Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(FailResponse.fail(HttpStatus.BAD_REQUEST.value(),
                        ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    // Request Body의 형식이 맞지 않아 발생하는 JSON parse Error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<FailResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(FailResponse.fail(HttpStatus.BAD_REQUEST.value(), ERR_INVALID_REQUEST_BODY_FORMAT.toString()));
    }
}