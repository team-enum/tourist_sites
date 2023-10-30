package com.enums.tourist.planner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Content;
import com.enums.tourist.domain.Planner;
import com.enums.tourist.planner.repository.ContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {
   private final ContentRepository contentRepository;

   @Transactional
   public Content save(Content content, Planner planner) {
      content.setPlanner(planner);
      return contentRepository.save(content);
   }

   public Content findById(Long id){
		Optional<Content> findContent = contentRepository.findById(id);
		if(!findContent.isPresent()){
			throw new NullPointerException("Content가 존재하지 않음.");
		}
		return findContent.get();
	}

   public List<Content> findAll(Planner planner){
		List<Content> findContent = contentRepository.findByPlanner(planner);
		return findContent;
	}

   @Transactional
   public Content update(Long placeId, String memo, String date) {
      Content content = findById(placeId);
      content.setMemo(memo);
      content.setDate(date);
      return content;
   }

   @Transactional
   public boolean delete(Long placeId) {
      Content content = findById(placeId);
      try {
         contentRepository.delete(content);
         return true;
      } catch (IllegalArgumentException e) {
         e.printStackTrace();
      }
      return false;
   }

}
