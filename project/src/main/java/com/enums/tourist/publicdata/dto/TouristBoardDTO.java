package com.enums.tourist.publicdata.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @ToString @NoArgsConstructor
public class TouristBoardDTO {
   private int numOfRows;
   private int pageNo;
   private int totalCount;

   @JsonProperty(value = "items")
   @Getter(lombok.AccessLevel.NONE)
   private TouristItems items;

   public List<TouristItemDTO> getList(){
      return items.getList();
   }

   // 내부 클래스
   @JsonIgnoreProperties(ignoreUnknown = true)
   @Getter @ToString @NoArgsConstructor
   class TouristItems{
      @JsonProperty(value = "item")
      private List<TouristItemDTO> list;
   }
}
