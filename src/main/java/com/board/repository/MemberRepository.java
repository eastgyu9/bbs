package com.board.repository;

import org.springframework.stereotype.Repository;

import com.board.domain.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class MemberRepository {

	public int save(Member member) {
		log.info("member = " + member);
		return 0;
	}

}
