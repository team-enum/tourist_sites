package com.enums.tourist.publicdata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Like;
import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.embedd.Tourist;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.repository.BoarderRepository;
import com.enums.tourist.publicdata.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private final BoarderRepository boarderRepository;
	private final LikeRepository likeRepository;

	// 게시판
	public Board findOne(Long contentId) {
		return boarderRepository.findByContentId(contentId);
	}

	@Transactional
	public Board findOne(Long contentId, TouristDTO touristDTO) {
		Board board = findOne(contentId);
		if(board == null){
         board = new Board();
         Tourist tourist = Tourist.builder()
            .contentId(contentId)
            .title(touristDTO.getTitle())
            .address(touristDTO.getAddress1())
            .image(touristDTO.getImage1())
            .build();
         board.setTourist(tourist);
         boarderRepository.save(board);
      }
		return boarderRepository.findByContentId(contentId);
	}

	// 좋아요
	@Transactional
	public boolean like(Board board, Member member) {

		Like like = likeRepository.findByMemberAndBoard(member, board);
		if (like != null) {
			likeRepository.delete(like);
			return false;
		}

		like = new Like();
		like.setMember(member);
		like.setBoard(board);
		likeRepository.save(like);
		return true;
	}

	public Long countLike(Board board) {
		return likeRepository.countByBoard(board);
	}

}
