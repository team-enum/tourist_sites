package com.enums.tourist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import com.enums.tourist.oauth.CustomOAuthUserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
   private final CustomOAuthUserService oAuthUserService;

   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

   @Bean
   public RestTemplate restTemplate(){
      return new RestTemplate();
   }

   @Bean
   public SecurityFilterChain configure(HttpSecurity http) throws Exception{
      http.authorizeHttpRequests((req) -> req
            .requestMatchers("/member/mypage**").hasAuthority("USER")
            .requestMatchers("/member/join**", "/member/login").anonymous()
            .anyRequest().permitAll())
         .formLogin(formLogin -> formLogin
            .loginPage("/member/login")
            .defaultSuccessUrl("/"))
         .logout((logout) -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true))
         .oauth2Login((oauth) -> oauth
            .loginPage("/member/login")
            .userInfoEndpoint((endpoint)->endpoint
               .userService(oAuthUserService)));
      http.csrf(c -> c.disable());
      
      return http.build();
   }
}
