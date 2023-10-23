package com.enums.tourist.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enums.tourist.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	private static Map<Long,Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Autowired
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}

	public List<Member> findAll() {

		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	public List<Member> findByname(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name)
				.getResultList();
	}
	
	public Member findOne(Long memberId) {
		return em.find(Member.class, memberId);
	}
	public Member findByLoginId(String loginId) {
		List<Member> all = findall();
		for(Member m : all) {
			if(m.getIdd().equals(loginId)) {
				return m;
			}
		}
		return null;
	}
	public List<Member> findall(){
		return new ArrayList<>(store.values());
	}
	public Member Save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	
}
