package com.enums.tourist.publicdata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @ToString
public class TouristItemDTO {
   @JsonProperty("contentid")
   private Long contentId;

   @JsonProperty("title")
   private String title;

   @JsonProperty("addr1")
   private String addr1;

   @JsonProperty("addr2")
   private String addr2;
   
}
