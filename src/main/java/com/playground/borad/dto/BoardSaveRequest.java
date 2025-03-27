package com.playground.borad.dto;

import com.playground.borad.domain.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoardSaveRequest(

        @Size(max = 32)
        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @Size(max = 3000)
        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @Size(max = 16)
        @NotBlank(message = "작성자를 입력해주세요.")
        String createdBy
) {

        public Board toEntity() {
                return Board.created(title, content, createdBy);
        }
}
