package com.enums.tourist.publicdata.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.Tourist;
import com.enums.tourist.publicdata.api.DataPortalRequest;
import com.enums.tourist.publicdata.dto.TouristListDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.repository.BoarderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DataPortalService {
   
   private final DataPortalRequest dataPortalRequest;
   private final BoarderRepository boarderRepository;

   public TouristListDTO findAll(Integer area, Integer contentTypeId , int page) throws IOException {
      return dataPortalRequest.areaBased(area, contentTypeId , page);
   }
   
   
   
   @Transactional
   public TouristDTO findOne(Long contentId) throws IOException{
      Board board = boarderRepository.findByContentId(contentId);
      if(board == null){
         board = new Board();
         board.setTourist(new Tourist(contentId));
         boarderRepository.save(board);
      }
      return dataPortalRequest.detailCommon(contentId);
   }
}
