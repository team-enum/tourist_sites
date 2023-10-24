package com.enums.tourist.publicdata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Bookmark;
import com.enums.tourist.domain.Like;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.repository.BoarderRepository;
import com.enums.tourist.publicdata.repository.BookmarkRepository;
import com.enums.tourist.publicdata.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
   private final BoarderRepository boarderRepository;
   private final BookmarkRepository bookmarkRepository;
   private final LikeRepository likeRepository;

   public Board findOne(Long contentId){
      return boarderRepository.findByContentId(contentId);
   }

   @Transactional
   public boolean bookmarking(Board board, Member member){
      
      Bookmark bookmark = bookmarkRepository.findByMemberAndBoard(member, board);
      if(bookmark != null){
         bookmarkRepository.delete(bookmark);
         return false;
      }
      
      bookmark = new Bookmark();
      bookmark.setMember(member);
      bookmark.setBoard(board);
      bookmarkRepository.save(bookmark);
      return true;
   }

   @Transactional
   public boolean like(Board board, Member member) {

      Like like = likeRepository.findByMemberAndBoard(member, board);
      if(like != null){
         likeRepository.delete(like);
         return false;
      }
      
      like = new Like();
      like.setMember(member);
      like.setBoard(board);
      likeRepository.save(like);
      return true;
   }

   
}
