package com.yuhan.unnamed.service;

import com.yuhan.unnamed.domain.Board;
import com.yuhan.unnamed.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getSeq()).get();
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getSeq());
    }

    @Override
    public void incrementCnt(Board board) {
        Board found = boardRepository.findById(board.getSeq()).get();
        found.setCnt(found.getCnt() + 1);
        boardRepository.save(found);
    }

    @Override
    public void likeBoard(Board board) {
        Board found = boardRepository.findById(board.getSeq()).get();
        found.setLikeCnt(found.getLikeCnt() + 1);
        boardRepository.save(found);
    }

    @Override
    public void dislikeBoard(Board board) {
        Board found = boardRepository.findById(board.getSeq()).get();
        found.setDislikeCnt(found.getDislikeCnt() + 1);
        boardRepository.save(found);
    }

    @Override
    public Board getBoard(Board board) {
        return boardRepository.findById(board.getSeq()).get();
    }

    @Override
    public List<Board> getBoardList(Board board) {
        List<Board> listBoard = boardRepository.findAll();
        return listBoard;
    }
}
