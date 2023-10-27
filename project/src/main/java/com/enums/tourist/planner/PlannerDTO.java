package com.enums.tourist.planner;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class PlannerDTO {
   private String memo;
   private String date;
   private Map<String, String> places;
}
