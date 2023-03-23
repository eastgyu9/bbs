package com.board.controller;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		boolean loginResult = memberService.login(member);
		if (loginResult) {
			// 로그인 시 해당 유저의 정보가 유지되어야 한다. session 이용
			session.setAttribute("loginEmail", member.getMemberEmail());
			return "index";
		} else {
			return "login";
		}
		
	}
}
