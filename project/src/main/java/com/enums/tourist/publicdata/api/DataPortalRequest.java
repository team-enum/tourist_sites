package com.enums.tourist.publicdata.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.enums.tourist.publicdata.dto.TouristBoardDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.enums.tourist.publicdata.dto.TouristItemDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataPortalRequest {
   private final String searchKeywordStrURL = "https://apis.data.go.kr/B551011/KorService1/searchKeyword1";
   private final String detailCommonStrURL = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
   private final String serviceKeyEncoding = "ke9Ra%2BRmOuOOM2PR2FnBx4UaSbuRAITt74KHFwdDPasmC9THZKRPrHOrG92O10ysFdjNEpBRkLn2D3VbxaS7kA%3D%3D";
   
   private String reading(String uri) throws IOException{
      URL url = new URL(uri);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
      StringBuilder sb = new StringBuilder();
      while(br.ready()) sb.append(br.readLine());
      return sb.toString();
   }

   public List<TouristItemDTO> searchKeyword(String keyword, int numOfRows, int pageNo) throws IOException{
      keyword = keyword != null ? URLEncoder.encode(keyword, StandardCharsets.UTF_8) : keyword;
      String uri = UriComponentsBuilder
         .fromUriString(searchKeywordStrURL)
         .queryParam("serviceKey", serviceKeyEncoding)
         .queryParam("keyword", keyword)
         .queryParam("MobileOS", "ETC")
         .queryParam("MobileApp", "AppTest")
         .queryParam("_type", "json")
         .queryParam("numOfRows", numOfRows)
         .queryParam("pageNo", pageNo)
         .build().toUriString()
      ;
      log.info("[searchKeyword] : " + uri);
      String responseBody = reading(uri);

      ObjectMapper mapper = new ObjectMapper();
      String findItems = mapper.readTree(responseBody).findPath("item").toString();
      // String findInfo = mapper.readTree(responseBody).findPath("body").toString();
      // TouristBoardDTO board = mapper.readValue(findInfo, TouristBoardDTO.class);
      // log.info("[board :: ]" + board.toString());

      List<TouristItemDTO> dtoList = mapper.readValue(findItems, new TypeReference<List<TouristItemDTO>>(){});
      return dtoList;
   }

   public TouristDTO detailCommon(Long contentId) throws IOException{
      String uri = UriComponentsBuilder
         .fromUriString(detailCommonStrURL)
         .queryParam("serviceKey", serviceKeyEncoding)
         .queryParam("contentId", contentId)
         .queryParam("MobileOS", "ETC")
         .queryParam("MobileApp", "AppTest")
         .queryParam("_type", "json")
         .queryParam("defaultYN", "Y")
         .queryParam("firstImageYN", "Y")
         .queryParam("areacodeYN", "Y")
         .queryParam("catcodeYN", "Y")
         .queryParam("addrinfoYN", "Y")
         .queryParam("mapinfoYN", "Y")
         .queryParam("overviewYN", "Y")
         .build().toUriString()
      ;
      log.info("[detailCommon] : " + uri);
      String responseBody = reading(uri);

      ObjectMapper mapper = new ObjectMapper();
      String findItem = mapper.readTree(responseBody).findPath("item").get(0).toString();

      TouristDTO dto = mapper.readValue(findItem, TouristDTO.class);
      return dto;
   }
}
