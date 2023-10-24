package com.enums.tourist.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDTO {
   private String username;
   private String password;
   private String realname;
}
