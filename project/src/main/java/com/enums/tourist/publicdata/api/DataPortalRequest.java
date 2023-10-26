package com.enums.tourist.publicdata.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.enums.tourist.publicdata.dto.TouristListDTO;
import com.enums.tourist.publicdata.dto.TouristDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataPortalRequest {
   
   private int numOfRows = 12;
   @Value("${public-data.korservice.url.area-based}")
   private String areaBasedURL;

   @Value("${public-data.korservice.url.keyword}")
   private String searchKeywordURL;

   @Value("${public-data.korservice.url.detail}")
   private String detailURL;

   @Value("${public-data.korservice.key.encoding}")
   private String serviceKey;

   private String reading(String uri, int timeout) throws IOException{
      URL url = new URL(uri);
      HttpURLConnection conn = null;
      try {
         conn = (HttpURLConnection) url.openConnection();
         conn.setReadTimeout(timeout);
      } catch (SocketTimeoutException e){
         log.error(e.getMessage());
         conn = (HttpURLConnection) url.openConnection();
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
      StringBuilder sb = new StringBuilder();
      while(br.ready()) sb.append(br.readLine());
      return sb.toString();
   }

   private String reading(String uri) throws IOException{
      return reading(uri,3000);
   }

   public TouristListDTO areaBased(Integer area, Integer contentTypeId, int pageNo) throws IOException{
      UriComponentsBuilder uriBuilder = UriComponentsBuilder
         .fromUriString(areaBasedURL)
         .queryParam("serviceKey", serviceKey)
         .queryParam("MobileOS", "ETC")
         .queryParam("MobileApp", "Tourist")
         .queryParam("_type", "json")
         .queryParam("listYN", "Y")
         .queryParam("arrange", "C")
         .queryParam("numOfRows", numOfRows)
         .queryParam("pageNo", pageNo)
         .queryParam("contentTypeId", contentTypeId)
         
         //.queryParam("contentTypeId", 32)
      ;
      
      if(area != null) uriBuilder.queryParam("areaCode", area);
      if(contentTypeId != null) uriBuilder.queryParam("contentTypeId", contentTypeId);

      String uri = uriBuilder
         .build().toUriString()
      ;
      log.info("[Request URL] : " + uri);
      String responseBody = reading(uri);

      ObjectMapper mapper = new ObjectMapper();
      String findInfo = mapper.readTree(responseBody).findPath("body").toString();
      TouristListDTO board = mapper.readValue(findInfo, TouristListDTO.class);
      
      return board;
   }

   public TouristListDTO searchKeyword(String keyword, int pageNo) throws IOException{
      keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
      String uri = UriComponentsBuilder
         .fromUriString(searchKeywordURL)
         .queryParam("serviceKey", serviceKey)
         .queryParam("keyword", keyword)
         .queryParam("MobileOS", "ETC")
         .queryParam("MobileApp", "AppTest")
         .queryParam("_type", "json")
         .queryParam("numOfRows", numOfRows)
         .queryParam("pageNo", pageNo)
         .build().toUriString()
      ;
      log.info("[searchKeyword] : " + uri);
      String responseBody = reading(uri, 15000);

      ObjectMapper mapper = new ObjectMapper();
      String findInfo = mapper.readTree(responseBody).findPath("body").toString();
      TouristListDTO board = mapper.readValue(findInfo, TouristListDTO.class);
      
      return board;
   }

   public TouristDTO detailCommon(Long contentId) throws IOException{
      String uri = UriComponentsBuilder
         .fromUriString(detailURL)
         .queryParam("serviceKey", serviceKey)
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
