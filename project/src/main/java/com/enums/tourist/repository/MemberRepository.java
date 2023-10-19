package com.enums.tourist.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enums.tourist.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	@Autowired
	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}
	
	public List<Member> findAll() {
//		List<Member> result = 
//			em.createQuery("select m from Member m", Member.class)
//			  .getResultList();
//		return result;
		
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}
	
	public List<Member> findByname(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
	}
	
	public Member findOne(Long memberId) {
		return em.find(Member.class, memberId);
	}
	
	public Member findByLoginId(String loginId) {
		List<Member> all = findAll();
		for( Member m : all ) {
			if( m.getLoginId().equals(loginId) ) {
				return m;
			}
		}
		return null;
		
	}
}
