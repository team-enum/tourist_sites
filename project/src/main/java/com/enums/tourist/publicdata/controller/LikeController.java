package com.enums.tourist.publicdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.publicdata.service.LikeService;

@Controller
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @ResponseBody
    @PostMapping("/detail/{contentId}/add")
    public String addLike(@PathVariable Long memberId, @PathVariable Long contentId) {
        boolean added = likeService.addLike(memberId, contentId);
        return added ? "Liked successfully" : "Already liked";
    }

    @ResponseBody
    @PostMapping("/detail/{contentId}/remove")
    public String removeLike(@PathVariable Long memberId, @PathVariable Long contentId) {
        boolean removed = likeService.removeLike(memberId, contentId);
        return removed ? "Like removed successfully" : "Like not found";
    }
}
