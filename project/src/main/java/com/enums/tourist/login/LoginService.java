package com.enums.tourist.login;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enums.tourist.domain.Member;
import com.enums.tourist.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;
	
	public List<Member> login(String id, String password) {
		List<Member> member = memberRepository.findByLoginId(id);
		
		if( member != null && ((Member) member).getId().equals(id)
				&& ((Member) member).getPassword().equals(password) ) {
			return member;
		}else {
			return null;
		}
	}
}















