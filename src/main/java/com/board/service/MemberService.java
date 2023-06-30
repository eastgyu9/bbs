package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.MemberVO;
import com.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public int save(MemberVO member) {
		int result = memberRepository.save(member);
		
		if (result > 0) {
			log.info("회원가입 완료! : " + "결과: " + result);
			return result;
		} else {
			log.info("회원가입 실패.. : " + "결과: " + result);
			return 0;
		}
	}

	public boolean login(MemberVO member) {
		// 1. Repo.login메소드에서 마이바티스로 보낸 쿼리가 입력된 회원 이메일과 패스워드를 조회
		// 조회 결과 일치한 컬럼이 있다면 결과는(이메일, 패스워드) loginMember 변수에 저장된다.
		// 일치하는 결과가 없다면 변수에 null이 저장된다.
		MemberVO loginMember = memberRepository.login(member);
		if (loginMember != null) {
			log.info("로그인 완료! : " + loginMember);
			// 변수가 null이 아니라면 true를 리턴한다.
			return true; // boolean 타입으로 loginMember가 null이 아니면 true를 리턴 => 로그인 성공	
		} else {
			// null일 경우 로그인 처리 실패 로직
			log.info("로그인 실패! 계정을 다시 확인해주세요. : " + loginMember);
			return false;
		}
	}

	// DB에서 이메일을 조회한 뒤 파라미터로 그 값을 받아와 리턴한다.
	public MemberVO findByEmailMemberInfo(String sessionEmail) {
		return memberRepository.findByEmailMemberInfo(sessionEmail);
	}
	
	// 아이디 조회
	public MemberVO findById(Long id) {
		return memberRepository.findById(id);
	}

	// 이름 조회
	public MemberVO findByMemberName(MemberVO member) {
		return memberRepository.findByMemberName(member);
	}

	// 이메일만 조회
	public MemberVO findByEmail(String sessionEmail) {
		return memberRepository.findByEmail(sessionEmail);
	}
	
	// 모든 회원 찾기
	public List<MemberVO> findAll() {
		return memberRepository.findAll();
	}
	
	// 삭제
	public void deleteMember(Long id) {
		memberRepository.deleteMember(id);
	}

	// 수정
	public boolean update(MemberVO member) {
		int result = memberRepository.update(member);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
}