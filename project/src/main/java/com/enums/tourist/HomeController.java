package com.enums.tourist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enums.tourist.domain.Area;
import com.enums.tourist.domain.ContentType;

@Controller
public class HomeController {
   
	@ModelAttribute("areaCodes")
	   public List<Area> areaCodes(){
			List<Area> areaCodes = new ArrayList<>();
			areaCodes.add(new Area(1, "서울"));
			areaCodes.add(new Area(2, "인천"));
			areaCodes.add(new Area(3, "대전"));
			areaCodes.add(new Area(4, "대구"));
			areaCodes.add(new Area(5, "광주"));
			areaCodes.add(new Area(6, "부산"));
			areaCodes.add(new Area(7, "울산"));
			areaCodes.add(new Area(8, "세종"));
			return areaCodes;
		}
	   
	   @ModelAttribute("contentTypes")
		public List<ContentType> contentTypes(){
			List<ContentType> contentTypes = new ArrayList<>();
			contentTypes.add(new ContentType(12, "관광지"));
			contentTypes.add(new ContentType(14, "문화시설"));
			contentTypes.add(new ContentType(15, "축제공연행사"));
			contentTypes.add(new ContentType(25, "여행코스"));
			contentTypes.add(new ContentType(28, "레포츠"));
			contentTypes.add(new ContentType(32, "숙박"));
			contentTypes.add(new ContentType(38, "쇼핑"));
			contentTypes.add(new ContentType(39, "음식점"));
			return contentTypes;
		}
	
	
	
   @GetMapping
   public String home(){
      return "home";
   }

   // 탬플릿 예시용 - 다 만들어질 때쯤 없앨 예정..
   @GetMapping("/template")
   public String test(){
      return "/fragments/example";
   }
}
