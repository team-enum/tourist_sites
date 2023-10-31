package com.enums.tourist.publicdata.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Board;
import com.enums.tourist.domain.embedd.Tourist;
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
   
   public TouristListDTO findAll(Integer area, Integer contentTypeId , String keyword, Integer pageNo) throws IOException{
      return dataPortalRequest.searchKeyword(area, contentTypeId ,keyword, pageNo);
   }
   
   @Transactional
   public TouristDTO findOne(Long contentId) throws IOException{
      Board board = boarderRepository.findByContentId(contentId);
      TouristDTO touristDTO = dataPortalRequest.detailCommon(contentId);
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
      return touristDTO;
   }

}
