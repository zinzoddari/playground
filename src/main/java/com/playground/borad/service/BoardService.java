package com.playground.borad.service;

import com.playground.borad.domain.Board;
import com.playground.borad.domain.NotFoundBoardException;
import com.playground.borad.dto.BoardDetailResponse;
import com.playground.borad.dto.BoardSaveRequest;
import com.playground.borad.dto.BoardUpdateRequest;
import com.playground.borad.repository.BoardCacheRepository;
import com.playground.borad.repository.BoardRepository;
import com.playground.infra.redis.publisher.RedisChannel;
import com.playground.infra.redis.publisher.SimpleRedisPublisher;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCacheRepository boardCacheRepository;

    private final SimpleRedisPublisher<Board> simpleRedisPublisher;

    @Transactional(readOnly = true)
    public BoardDetailResponse getBoard(final Long id) {
        final Board board = boardCacheRepository.get(id)
                .orElseThrow(NotFoundBoardException::new);

        return BoardDetailResponse.created(board.getTitle(),
                board.getContent(),
                board.getCreatedBy(),
                board.getCreatedDate());
    }

    @Transactional
    public void register(@NotNull final BoardSaveRequest request) {
        final Board board = boardRepository.save(request.toEntity());

        boardCacheRepository.save(board.getId(), board);

        simpleRedisPublisher.publish(RedisChannel.OPEN_SEARCH, board);
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

        boardRepository.delete(board);
    }
}
