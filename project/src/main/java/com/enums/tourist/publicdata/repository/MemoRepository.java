package com.enums.tourist.publicdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enums.tourist.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
