package com.enums.tourist.publicdata.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.enums.tourist.publicdata.api.DataPortalRequest;
import com.enums.tourist.publicdata.dto.TouristListDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataPortalService {
   
   private final DataPortalRequest dataPortalRequest;

   public TouristListDTO findAll(Integer area, Integer contentTypeId , int page) throws IOException {
      return dataPortalRequest.areaBased(area, contentTypeId , page);
   }
   
   public TouristListDTO findAll(Integer area, Integer contentTypeId , String keyword, Integer pageNo) throws IOException{
      return dataPortalRequest.searchKeyword(area, contentTypeId ,keyword, pageNo);
   }
   
   public TouristDTO findOne(Long contentId) throws IOException{
      TouristDTO touristDTO = dataPortalRequest.detailCommon(contentId);
      return touristDTO;
   }
}
