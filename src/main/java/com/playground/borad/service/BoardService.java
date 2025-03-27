package com.playground.borad.service;

import com.playground.borad.domain.Board;
import com.playground.borad.domain.NotFoundBoardException;
import com.playground.borad.dto.BoardDetailResponse;
import com.playground.borad.dto.BoardSaveRequest;
import com.playground.borad.dto.BoardUpdateRequest;
import com.playground.borad.repository.BoardRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public BoardDetailResponse getBoard(final Long id) {
        final Board board = boardRepository.findById(id)
                .orElseThrow(NotFoundBoardException::new);

        return BoardDetailResponse.created(board.getTitle(),
                board.getContent(),
                board.getCreatedBy(),
                board.getCreatedDate());
    }

    @Transactional
    public void register(@NotNull final BoardSaveRequest request) {
        boardRepository.save(request.toEntity());
    }

    @Transactional
    public void modify(@NotNull final Long id, @NotNull final BoardUpdateRequest request) {
        final Board board = boardRepository.findById(id)
                .orElseThrow(NotFoundBoardException::new);

        board.modify(request.title(), request.content(), request.updateBy());
    }

    @Transactional
    public void remove(final Long id) {
        final Board board = boardRepository.findById(id)
                .orElseThrow(NotFoundBoardException::new);

        boardRepository.deleteById(id);
    }
}
