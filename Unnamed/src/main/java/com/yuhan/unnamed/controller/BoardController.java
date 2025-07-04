package com.yuhan.unnamed.controller;

import com.yuhan.unnamed.domain.Board;
import com.yuhan.unnamed.domain.Comment;
import com.yuhan.unnamed.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    public String getBoardList(Board board, Model model) {
        model.addAttribute("boardList", boardService.getBoardList(board));
        return "board/boardList";
    }

    @PostMapping("/getBoardList")
    public String getBoardListPost(Board board, Model model) {
        model.addAttribute("boardList", boardService.getBoardList(board));
        return "board/boardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoardView() {
        return "/board/writeBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @AuthenticationPrincipal UserDetails userDetails) {
        board.setWriter(userDetails.getUsername());
        boardService.insertBoard(board);
        return "forward:getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        boardService.incrementCnt(board);

        Board found = boardService.getBoard(board);
        List<Comment> comments = found.getComments();

        model.addAttribute("username", userDetails != null ? userDetails.getUsername() : "");
        model.addAttribute("board", found);
        model.addAttribute("comments", comments);

        return "board/boardDetail";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "redirect:/getBoard?seq=" + board.getSeq();
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "forward:getBoardList";
    }

    @GetMapping("/modifyBoard")
    public String modifyBoardView(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "board/modifyBoard";
    }

    @GetMapping("/likeBoard")
    public String likeBoard(Board board) {
        boardService.likeBoard(board);
        return "redirect:/getBoard?seq=" + board.getSeq();
    }

    @GetMapping("/dislikeBoard")
    public String dislikeBoard(Board board) {
        boardService.dislikeBoard(board);
        return "redirect:/getBoard?seq=" + board.getSeq();
    }
}
