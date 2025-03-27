package com.playground.infra.exception;

public class PlayGroundBusinessException extends RuntimeException {

    public PlayGroundBusinessException(final String message) {
        super(message);
    }
}
