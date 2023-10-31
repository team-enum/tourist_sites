package com.enums.tourist.publicdata.repository;

import com.enums.tourist.domain.Bookmark;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.Board;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{
   
   long countByMemberAndBoard(Member member, Board board);
   Bookmark findByMemberAndBoard(Member member, Board board);
   Long countByBoard(Board board);
   List<Bookmark> findAllByMember(Member member, Sort sort);

}
