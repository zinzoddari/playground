package com.playground.borad.repository;

import com.playground.borad.domain.Board;
import java.util.Optional;

public interface BoardCacheRepository {

    void save(final Long id, final Board board);

    Optional<Board> get(final Long id);
}
