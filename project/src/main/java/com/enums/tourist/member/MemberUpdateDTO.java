package com.enums.tourist.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberUpdateDTO {

   @NotBlank(message = "이름은 필수 입력 값입니다.")
   private String realname;

   private String password;
   
   private String passwordConfirm;

   @NotBlank(message = "이메일은 필수 입력 값입니다.")
   @Email(message = "이메일 형식에 맞지 않습니다.")
   private String email;

   public boolean passwordMatch(){
      return password.equals(passwordConfirm);
   }
}
