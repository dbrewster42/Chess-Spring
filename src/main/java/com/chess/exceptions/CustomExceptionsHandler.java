package com.chess.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidMoveException.class)
    protected ResponseEntity<Object> resolveInvalidMove(InvalidMoveException e, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.EXPECTATION_FAILED.value(),
                HttpStatus.EXPECTATION_FAILED.getReasonPhrase(),
                e.getMessage(),
                req.getDescription(true));
        return handleExceptionInternal(e, errorResponse.toString(), new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, req);
    }

    @ExceptionHandler(value = MustDefeatCheckException.class)
    protected ResponseEntity<Object> resolveCheckStatus(MustDefeatCheckException e, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.EXPECTATION_FAILED.value(),
                HttpStatus.EXPECTATION_FAILED.getReasonPhrase(),
                e.getMessage(),
                req.getDescription(true));
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, req);
    }
}
