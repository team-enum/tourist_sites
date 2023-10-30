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
public class Place {
   @Column(name = "place_id")
   private Long id;
   @Column(name = "place_name")
   private String name;
   private String address;
   private String road_address;
   private String phone;
   private String url;
   
   private float x;
   private float y;
}
