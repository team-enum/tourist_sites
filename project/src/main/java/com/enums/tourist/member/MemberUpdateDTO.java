package com.enums.tourist.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberUpdateDTO {
   private String password;
   private String realname;
   private String email;
}
