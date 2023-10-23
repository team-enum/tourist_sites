package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
}
