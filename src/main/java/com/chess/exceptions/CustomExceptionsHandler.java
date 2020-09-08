package com.chess.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidMoveException.class)
    @ResponseBody
    protected ResponseEntity<Object> resolveInvalidMove(InvalidMoveException e, WebRequest req) throws Exception {
    //public ErrorResponse resolveInvalidMove(InvalidMoveException e, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.EXPECTATION_FAILED.value(),
                HttpStatus.EXPECTATION_FAILED.getReasonPhrase(),
                e.getMessage(),
                req.getDescription(true));
        return handleExceptionInternal(e, errorResponse.toString(), new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, req);
        //return handleException(e, req);
        //return errorResponse;
    }
//    @ExceptionHandler(value = InvalidMoveException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
//    public ErrorResponse resolveInvalidMove(InvalidMoveException e){
//        return new ErrorResponse(417, e.getMessage());
//    }

    @ExceptionHandler(value = MustDefeatCheckException.class)
    protected ResponseEntity<Object> resolveCheckStatus(MustDefeatCheckException e, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.EXPECTATION_FAILED.value(),
                HttpStatus.EXPECTATION_FAILED.getReasonPhrase(),
                e.getMessage(),
                req.getDescription(true));
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, req);
    }
}
