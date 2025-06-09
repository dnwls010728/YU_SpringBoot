package com.yuhan.unnamed.persistence;

import com.yuhan.unnamed.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
