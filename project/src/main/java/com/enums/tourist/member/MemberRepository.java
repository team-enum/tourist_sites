package com.enums.tourist.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
   
   public Member findByUsername(String username);
   public Long countByUsername(String username);
}
