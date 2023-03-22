package com.board.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.Member;
import com.board.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/join")
	public String memberAddForm() {
		return "joinMember";
	}
	
	@PostMapping("/join")
	public String memberAdd(@ModelAttribute Member member) {
		int result = memberService.save(member);
		
		if (result > 0) {
			return "login";
		} else {
			return "joinMember";
		}
	} 
}
