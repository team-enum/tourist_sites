package com.enums.tourist.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.tourist.oauth.dto.KakaoToken;
import com.enums.tourist.oauth.dto.KakaoUserInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OauthKakaoController {
   
   private final OauthKakaoRequest kakaoOauth;
   
   @GetMapping("/kakao/oauth")
   @ResponseBody
   public Object receiveAuthorizeCode(@RequestParam String code){
      KakaoToken token = kakaoOauth.receiveToken(code);
      KakaoUserInfo userInfo = kakaoOauth.receiveUserInformation(token.getAccess_token());
      
      return userInfo;
   }
   
}
