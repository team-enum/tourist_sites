package com.enums.tourist.publicdata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @ToString
public class TouristDTO {
   @JsonProperty("contentid")
   private Long contentId;

   @JsonProperty("title")
   private String title;

   @JsonProperty("tel")
   private String tel;

   @JsonProperty("addr1")
   private String address1;

   @JsonProperty("addr2")
   private String address2;

   @JsonProperty("firstimage")
   private String image1;

   @JsonProperty("firstimage2")
   private String image2;

   @JsonProperty("homepage")
   private String homepage;

   @JsonProperty("overview")
   private String overview;

   private int likeCount;
}
