package com.yuhan.unnamed.controller;

import com.yuhan.unnamed.domain.Comment;
import com.yuhan.unnamed.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/insertComment")
    public String insertComment(Comment comment, @AuthenticationPrincipal UserDetails user) {
        comment.setWriter(user.getUsername());
        commentService.insertComment(comment);
        return "redirect:/getBoard?seq=" + comment.getBoardSeq();
    }

    @GetMapping("/deleteComment")
    public String deleteComment(Comment comment) {
        commentService.deleteComment(comment);
        return "redirect:/getBoard?seq=" + comment.getBoardSeq();
    }

}
