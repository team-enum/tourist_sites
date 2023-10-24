package com.enums.tourist.publicdata.repository;

import com.enums.tourist.domain.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.Board;
import java.util.List;



public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{
   
   long countByMemberAndBoard(Member member, Board board);
   Bookmark findByMemberAndBoard(Member member, Board board);
}
