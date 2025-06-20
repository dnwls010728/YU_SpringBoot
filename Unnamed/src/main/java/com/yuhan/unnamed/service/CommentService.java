package com.yuhan.unnamed.service;

import com.yuhan.unnamed.domain.Comment;
import com.yuhan.unnamed.persistence.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void insertComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        Comment findComment = commentRepository.findById(comment.getSeq()).get();
        findComment.setContent(comment.getContent());
        commentRepository.save(findComment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.deleteById(comment.getSeq());
    }

}
