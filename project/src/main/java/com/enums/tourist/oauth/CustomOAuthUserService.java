package com.enums.tourist.oauth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.enums.MemberType;
import com.enums.tourist.member.MemberRepository;
import com.enums.tourist.security.MemberDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService{

   private final MemberRepository memberRepository;

   @Override
   @Transactional
   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
      OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
      OAuth2User oAuth2User = delegate.loadUser(userRequest);

      Map<String, Object> attributes = oAuth2User.getAttributes();
      log.info(attributes.toString());
      String userId = String.valueOf(attributes.get("id"));

      Member member = memberRepository.findByUsername(userId);
      
      if(member == null){
         Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
         Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

         String nickname = String.valueOf(profile.get("nickname"));
         String email = String.valueOf(kakaoAccount.get("email"));

         member = new Member();
         member.setCreateDate(LocalDateTime.now());
         member.setMemberType(MemberType.Kakao);
         member.setUsername(userId);
         member.setRealname(nickname);
         member.setEmail(email);
         memberRepository.save(member);
      }

      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("USER"));

      return new MemberDetails(member, authorities, attributes);
   }
   
}
