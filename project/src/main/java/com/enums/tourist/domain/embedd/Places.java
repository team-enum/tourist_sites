package com.enums.tourist.domain.embedd;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class Places {
   @Column(name = "places_id")
   private Long id;
   private String place_name;
   private String address_name;
   private String road_address_name;
   private String phone;
   private String place_url;
   
   private float x;
   private float y;
}