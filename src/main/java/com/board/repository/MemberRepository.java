package com.board.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.board.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
	/* MyBatis에서 제공하는 CRUD 메소드 사용을 위한 SQL세션템플릿 클래스
	 * Mapper.xml에 선언된 NameSpace.id [Member.save] 
	 */
	private final SqlSessionTemplate sql;

	public int save(Member member) {
		log.info("Save 메소드 실행 = " + member);
		return sql.insert("Member.save", member);
	}

	public Member login(Member member) {
		log.info("Login 메소드 실행 = " + member);
		return sql.selectOne("Member.login", member);
	}

}
