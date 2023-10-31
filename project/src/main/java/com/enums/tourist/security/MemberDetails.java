package com.enums.tourist.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.enums.tourist.domain.Member;

public class MemberDetails implements UserDetails, OAuth2User {
   // 회원 객체
   private Member member;
   // 권한
   private final Set<GrantedAuthority> authorities;
   // OAuth의 속성
   private Map<String, Object> attributes;

   public MemberDetails(Member member, Collection<? extends GrantedAuthority> authorities){
      this.member = member;
      this.authorities = Set.copyOf(authorities);
   }

   public MemberDetails(Member member, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes){
      this.member = member;
      this.authorities = Set.copyOf(authorities);
      this.attributes = attributes;
   }

   public Member getMember(){
      return this.member;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
   }

   @Override
   public String getPassword() {
      return member.getPassword();
   }

   @Override
   public String getUsername() {
      return member.getUsername();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public Map<String, Object> getAttributes() {
      return attributes;
   }

   @Override
   public String getName() {
      return null;
   }

   
   
}
