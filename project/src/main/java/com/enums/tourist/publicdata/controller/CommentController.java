package com.enums.tourist.publicdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enums.tourist.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	// 댓글 등록
	@PostMapping("/detail/{contentId}/comments")
	public String addComment(@PathVariable("contentId") Long contentId, @RequestParam("comment") String comment,
			Model model) {
		commentService.addComment(contentId, comment);
		model.addAttribute("message", "댓글이 등록되었습니다.");

		return "redirect:/tourist/detail/" + contentId; // 댓글 등록 후 상세 페이지로 리다이렉트
	}

	// 댓글 수정
	@PostMapping("/detail/{contentId}/comments/{commentId}/edit")
	public String editComment(@PathVariable("contentId") Long contentId, @PathVariable("commentId") Long commentId, @RequestParam(value = "comment", required = false) String updatedComment, Model model) {
	    if (updatedComment != null) {
	        commentService.updateCommentText(commentId, updatedComment);
	        model.addAttribute("message", "댓글이 수정되었습니다.");
	    } else {
	        model.addAttribute("error", "댓글이 제공되지 않았습니다.");
	    }
	    return "redirect:/tourist/detail/" + contentId;
	}


	// 댓글 삭제
	@PostMapping("/detail/{contentId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable("contentId") Long contentId, @PathVariable("commentId") Long commentId, Model model) {
        commentService.deleteComment(commentId);
        model.addAttribute("message", "댓글이 삭제되었습니다.");
        return "redirect:/tourist/detail/" + contentId; // 댓글 삭제 후 상세 페이지로 리다이렉트
    }
}
