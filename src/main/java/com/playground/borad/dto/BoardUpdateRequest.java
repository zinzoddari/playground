package com.playground.borad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoardUpdateRequest(

        String title,

        String content,

        @Size(max = 16)
        @NotBlank(message = "작성자를 입력해주세요.")
        String updateBy
) {
}
