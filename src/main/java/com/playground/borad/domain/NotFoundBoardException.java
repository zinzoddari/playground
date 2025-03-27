package com.playground.borad.domain;

import com.playground.infra.exception.PlayGroundBusinessException;

public class NotFoundBoardException extends PlayGroundBusinessException {

    public NotFoundBoardException() {
        super("유효한 게시글이 아닙니다.");
    }
}
