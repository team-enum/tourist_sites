package com.enums.tourist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
   
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
