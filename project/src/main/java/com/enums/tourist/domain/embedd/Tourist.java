package com.enums.tourist.domain.embedd;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PROTECTED) @ToString
public class Tourist {
   private Long contentId;
   private String title;
   private String address;
   private String image;
}
