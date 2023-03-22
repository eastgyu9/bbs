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
	
}
