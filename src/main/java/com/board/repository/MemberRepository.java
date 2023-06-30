package com.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.board.domain.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
	/* MyBatis에서 제공하는 CRUD 메소드 사용을 위한 memberMapperSessionTemplate 클래스
	 * Mapper.xml에 정의된 쿼리를 DB에 보내고 결과값을 파라미터로 받는다.("네임스페이스명.id", 파라미터);
	 * Mapper.xml에 선언된 NameSpace.id [MemberVO.save] 
	 * ("MemberVO.save", member); -> ("매퍼 네임스페이스.id",파라미터타입);
	 */
	// Mapper.xml에 정의된 쿼리를 DB에 보내고 결과값을 파라미터로 받는다.("네임스페이스명.id", 파라미터);
	private final SqlSessionTemplate memberMapper;
	
	public int save(MemberVO member) {
		log.info("Mapper 회원가입 : " + member);
		return memberMapper.insert("MemberVO.save", member);
	}

	public MemberVO login(MemberVO member) {
		log.info("Mapper 로그인 : " + "\n" + member);
		return memberMapper.selectOne("MemberVO.login", member);
	}
	
	public List<MemberVO> findAll() {
		return memberMapper.selectList("MemberVO.findAll");
	}

	public MemberVO findById(Long id) {
		log.info("Mapper ID 조회 : " + id);
		return memberMapper.selectOne("MemberVO.findById", id);
	}
	
	public MemberVO findByMemberName(MemberVO member) {
		log.info("Mapper 이름 조회 : " + member);
		return memberMapper.selectOne("MemberVO.findByMemberName",member);
	}

	public MemberVO findByEmail(String sessionEmail) {
		log.info("Mapper 이메일 1건 조회 : " + sessionEmail);
		return memberMapper.selectOne("MemberVO.findByEmail",sessionEmail);
	}
 
	public MemberVO findByEmailMemberInfo(String sessionEmail) {
		log.info("Mapper 이메일로 회원 정보 조회 : = " + sessionEmail);
		return memberMapper.selectOne("MemberVO.findByEmailMemberInfo", sessionEmail);
	}

	public void deleteMember(Long id) {
		log.info("Mapper 삭제 : " + id + "번 회원이 삭제되었습니다.");
		memberMapper.delete("MemberVO.delete", id);		
	}

	// 업데이트 메소드
	public int update(MemberVO member) {
		log.info("Mapper 수정 : " + member + "가 수정되었습니다.");
		return memberMapper.update("MemberVO.update", member);
	}
}
