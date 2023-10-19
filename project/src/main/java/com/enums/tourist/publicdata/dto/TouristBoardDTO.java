package com.enums.tourist.publicdata.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @ToString
public class TouristBoardDTO {
   private int numOfRows;
   private int pageNo;
   private int totalCount;

   @JsonProperty(value = "items")
   private List<TouristItemDTO> item;
}

