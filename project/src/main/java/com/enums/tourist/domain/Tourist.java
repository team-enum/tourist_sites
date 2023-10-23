package com.enums.tourist.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class Tourist {
   private Long contentId;
}
