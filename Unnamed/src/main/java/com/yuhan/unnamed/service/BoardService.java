package com.yuhan.unnamed.service;

import com.yuhan.unnamed.domain.Board;

import java.util.List;

public interface BoardService {
    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Board board);
    void incrementCnt(Board board);
    Board getBoard(Board board);
    List<Board> getBoardList(Board board);
}
