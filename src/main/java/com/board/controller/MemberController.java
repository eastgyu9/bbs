package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.MemberVO;
import com.board.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	/* 회원가입 */
	@GetMapping("/join")
	public String memberAddForm() {
		return "joinMember";
	}
	
	@PostMapping("/join")
	public String memberAdd(@ModelAttribute MemberVO member) {
		int result = memberService.save(member);
		
		if (result > 0) {
			log.info("가입이 완료되었습니다! " + member);
			return "redirect:/member/login";
		} else {
			log.info("회원가입에 실패했습니다. " + member);
			return "redirect:/member/joinMember";
		}
	}
	
	/* 로그인 */
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberVO member, HttpSession session, Model model) {
		boolean loginResult = memberService.login(member);
		if (loginResult) {
			session.setAttribute("sessionEmail", memberService.findByEmail(member.getMemberEmail()));
			session.setAttribute("sessionName", memberService.findByMemberName(member));
			session.setAttribute("memberID", memberService.findById(member.getId())); // 사용자 ID 세션 저장
			
			return "redirect:/";
		} else {
			return "redirect:/member/login"; 
		}
	}
	
	@GetMapping("/list")
	public String findAllMember(Model model) {
		List<MemberVO> memberVOList = memberService.findAll();
		model.addAttribute("memberList", memberVOList);
		return "memberList";
	}

	// id를 통한 상세 조회의 경우 http://localhost:8080/member/1
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		if (memberService.findById(id) == null) {
			log.info("해당 ID는 DB에서 조회되지 않습니다! 조회ID: " + id);
			model.addAttribute("memberID", id);
			//throw new RuntimeException();
		}
		MemberVO memberVO = memberService.findById(id);
		model.addAttribute("member", memberVO);
		return "memberDetail";
	}
		

	
//	@GetMapping("/update")
//	public String updateMember(HttpSession session, Model model) {
//		// 1. 세션에 저장된 이메일 가져오기
//		//    변수에 담기위해 String 타입의 리턴을 사용했다. 
//		//    session에서 주는 값은 Object 타입이다. 형변환 필요!
//		//    변수 loginMemberEmail에 세션에 저장된 loginEmail을 키로 가져온다.
//		MemberVO loginMemberEmail = (MemberVO) session.getAttribute("loginMemberEmail");
//		// 2. loginMemberEmail 계정을 DB에서 조회한 뒤 memberVO로 가져온다.
//		MemberVO memberVO = memberService.findByMemberEmail(loginMemberEmail);
//		// 3. 가져온 memberVO를 "member"라는 키로 model에 저장해 View로 전달한다.
//		model.addAttribute("member", memberVO);
//		return "memberUpdate";
//	}
	
	@GetMapping("/update")
	public String updateMember(HttpSession session, Model model) {
	    // 1. 세션에서 MemberVO 객체 가져오기
		//    sessionEmail은 로그인된 세션 정보가 담겨 MemberVO 타입의 sessionMember에 저장.
	    MemberVO sessionMember = (MemberVO) session.getAttribute("sessionEmail");
	    
	    // 2. 세션에 저장된 회원 정보가 null이거나 이메일이 null인 경우, 로그인 페이지로 리다이렉트 또는 예외 처리 등을 수행
	    if (sessionMember == null) {
	        // 로그인 페이지로 리다이렉트 또는 예외 처리
	    	log.info("세션이 만료되었습니다. 다시 로그인해 주세요!" + sessionMember);
	        return "redirect:/member/login";
	    }
	    
	    // 3. 이메일에 해당하는 회원 정보 전체 조회
	    sessionMember = memberService.findByEmailMemberInfo(sessionMember.getMemberEmail());
	    
	    // 4. 회원 정보를 모델에 추가하여 View로 전달
	    model.addAttribute("member", sessionMember);
	    return "memberUpdate";
	}
	
	@PostMapping("/update")
	public String updateMember (@ModelAttribute MemberVO member) {
		boolean result = memberService.update(member);
		if (result) {
			return "redirect:/member/" + member.getId();
		} else {
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String loginForm(HttpSession session) {
		// 1. 현재 세션에 있는 회원 ID를 가져온다.
		Long memberID = (Long) session.getAttribute("memberID");
		
		// 2. 세션에서 가져온 회원 ID를 조회한 뒤 memberVO에 회원 정보를 저장한다.
		MemberVO memberVO = memberService.findById(memberID);
		
		if(memberVO == null) {
			// 회원 ID가 조회되지 않는다면 회원이 삭제된 것으로 세션을 강제로 만료시킨다.
			session.invalidate();
			log.info("로그아웃.. " + session);
		}
		return "redirect:/member/login";
	}
	
	@GetMapping("/delete")
	public String deleteMember(@RequestParam("id") Long id, HttpSession session) {

		memberService.deleteMember(id);
		
		return "redirect:/member/list";
	}
}
