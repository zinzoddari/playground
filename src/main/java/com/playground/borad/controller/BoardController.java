package com.playground.borad.controller;

import com.playground.borad.dto.BoardDetailResponse;
import com.playground.borad.dto.BoardSaveRequest;
import com.playground.borad.dto.BoardUpdateRequest;
import com.playground.borad.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public BoardDetailResponse getBoard(@PathVariable final Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    public void register(@RequestBody @Valid final BoardSaveRequest request) {
        boardService.register(request);
    }

    @PutMapping("/{id}")
    public void modify(@PathVariable final Long id, @RequestBody @Valid final BoardUpdateRequest request) {
        boardService.modify(id, request);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final Long id) {
        boardService.remove(id);
    }
}
