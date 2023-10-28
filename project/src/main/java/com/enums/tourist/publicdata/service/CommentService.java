package com.enums.tourist.publicdata.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Comment;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.repository.CommentRepository;

@Service
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void addComment(Long contentId, String commentText, Member member) {
        Comment comment = new Comment();
        comment.setContentId(contentId);
        comment.setCommentText(commentText);
        comment.setMember(member);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateCommentText(Long commentId, String updatedComment) {
        commentRepository.updateCommentTextByCommentId(commentId, updatedComment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getCommentsByContentId(Long contentId) {
        return commentRepository.findAllByContentId(contentId);
    }
}
