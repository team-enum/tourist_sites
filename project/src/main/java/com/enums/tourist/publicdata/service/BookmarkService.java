package com.enums.tourist.publicdata.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Bookmark;
import com.enums.tourist.domain.Member;
import com.enums.tourist.publicdata.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

   private final BookmarkRepository bookmarkRepository;
   // 북마크
	@Transactional
	public boolean bookmarking(Board board, Member member) {

		Bookmark bookmark = bookmarkRepository.findByMemberAndBoard(member, board);
		if (bookmark != null) {
			bookmarkRepository.delete(bookmark);
			return false;
		}

		bookmark = new Bookmark();
		bookmark.setMember(member);
		bookmark.setBoard(board);
		bookmarkRepository.save(bookmark);
		return true;
	}

	public List<Bookmark> findAll(Member member){
		return bookmarkRepository.findAllByMember(member, Sort.by(Sort.Direction.DESC, "id"));
	}

	public Bookmark findOne(Member member, Board board){
		return bookmarkRepository.findByMemberAndBoard(member, board);
	}
}
