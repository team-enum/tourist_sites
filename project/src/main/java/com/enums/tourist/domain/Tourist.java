package com.enums.tourist.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor
public class Tourist {
   private Long contentId;
   private String title;
}
