package com.chess.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="You Are In Check")
public class MustDefeatCheckException extends RuntimeException {
    public MustDefeatCheckException(String msg){ super(msg); }
}
