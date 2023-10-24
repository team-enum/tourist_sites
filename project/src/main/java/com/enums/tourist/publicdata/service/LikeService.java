package com.enums.tourist.publicdata.service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Like;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.repository.LikeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public boolean addLike(Long memberId, Long contentId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndBoardId(memberId, contentId);
        if (existingLike.isPresent()) {
            return false;
        }

        Like newLike = new Like();
        Member member = new Member(); // Assuming Member and Board are entities
        Board board = new Board();
        newLike.setMember(member);
        newLike.setBoard(board);
        likeRepository.save(newLike);
        return true;
    }

    @Transactional
    public boolean removeLike(Long memberId, Long contentId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndBoardId(memberId, contentId);
        if (existingLike.isEmpty()) {
            return false;
        }

        likeRepository.delete(existingLike.get());
        return true;
    }
}
