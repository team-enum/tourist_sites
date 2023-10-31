package com.enums.tourist.member;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDTO {

	@Column(unique = true)
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;
    
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	private String password;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
    private String realname;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
}
