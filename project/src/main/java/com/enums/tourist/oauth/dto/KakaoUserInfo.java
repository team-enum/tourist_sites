package com.enums.tourist.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @ToString
public class KakaoUserInfo {
   private Long id;
   private String connected_at;
   private Properties properties;
   
   @JsonIgnoreProperties(ignoreUnknown = true)
   @Getter @ToString @NoArgsConstructor
public
   class Properties{
      private String nickname;
      private String profile_image;
      private String thumbnail_image;
   }
}
