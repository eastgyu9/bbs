package com.board.service;

import org.springframework.stereotype.Service;

import com.board.domain.Member;
import com.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public int save(Member member) {
		return memberRepository.save(member);
	}

	public boolean login(Member member) {
		Member loginMember = memberRepository.login(member);
		if (loginMember != null) {
			return true; // boolean 타입으로 loginMember가 null이 아니면 true를 리턴 => 로그인 성공	
		} else {
			return false;
		}
	}
	
}
