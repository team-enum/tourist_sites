package com.enums.tourist.publicdata.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.enums.tourist.publicdata.api.DataPortalRequest;
import com.enums.tourist.publicdata.dto.TouristBoardDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataPortalService {
   
   private final DataPortalRequest dataPortalRequest;
   private final int numOfRows = 20;

   public TouristBoardDTO findAll(String keyword ,int page) throws IOException {
      return dataPortalRequest.searchKeyword(keyword, numOfRows, page);
   }
   
   public TouristDTO findOne(Long contentId) throws IOException{
      return dataPortalRequest.detailCommon(contentId);
   }
}
