package com.enums.tourist.oauth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<kakaoUser, Long> {
	kakaoUser findByKakaoUserId(String kakaoUserId);
    
}
