package com.enums.tourist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Member;
import com.enums.tourist.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public Long join(Member member) throws IllegalAccessException {
		// 중복회원 검증 로직 추가
		memberRepository.save(member);
		return member.getId();
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member login(String loginId, String password) {
		Member member = memberRepository.findByLoginId(loginId);
		
		if( member != null && member.getLoginId().equals(loginId)
				&& member.getPassword().equals(password) ) {
			return member;
		}else {
			return null;
		}
	}
}
