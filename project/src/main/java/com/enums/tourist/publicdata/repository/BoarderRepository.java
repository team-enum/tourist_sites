package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enums.tourist.domain.Board;

public interface BoarderRepository extends JpaRepository<Board, Long>{
   
   @Query("SELECT b FROM Board b WHERE b.tourist.contentId = :contentId")
   public Board findByContentId(@Param("contentId") Long contentId);
}
