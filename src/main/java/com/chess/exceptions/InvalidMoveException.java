package com.chess.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason="Invalid Move")
public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String msg){ super(msg); }
}
