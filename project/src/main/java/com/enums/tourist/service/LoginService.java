package com.enums.tourist.service;

import org.springframework.stereotype.Service;

import com.enums.tourist.domain.Member;
import com.enums.tourist.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final MemberRepository memberRepository;
	
	public Member login(String idd,String password) {
		Member member = memberRepository.findByLoginId(idd);
		
		if(member != null && member.getIdd().equals(idd)
				&& member.getPassword().equals(password)) {
			return member;
		}else {
			return null;
		}
	}
}
