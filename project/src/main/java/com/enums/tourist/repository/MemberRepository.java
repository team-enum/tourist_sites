package com.enums.tourist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

