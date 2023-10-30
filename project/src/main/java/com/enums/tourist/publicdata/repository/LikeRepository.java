package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Like;
import com.enums.tourist.domain.Member;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
   Like findByMemberAndBoard(Member member, Board board);
   Long countByBoard(Board board);
}
