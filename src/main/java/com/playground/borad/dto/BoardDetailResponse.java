package com.playground.borad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BoardDetailResponse(

        @NotBlank
        String title,

        @NotBlank
        String content,

        @NotBlank
        String createdBy,

        @NotNull
        LocalDateTime createdDate
) {

        public static BoardDetailResponse created(final String title, final String content, final String createdBy, LocalDateTime createdDate) {
                return new BoardDetailResponse(title, content, createdBy, createdDate);
        }
}
