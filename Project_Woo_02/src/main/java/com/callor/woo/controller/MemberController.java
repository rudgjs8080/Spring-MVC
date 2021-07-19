package com.callor.woo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.woo.model.MemberVO;
import com.callor.woo.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	protected final MemberService mService;
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		
		return "/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO memberVO,HttpSession hSession,Model model) {
		memberVO = mService.login(memberVO, model);
		
		// id없음 또는 pw틀림 등으로 null값이 리턴되면
		if(memberVO == null) {
			//model.addAttribute("MSG","로그인 실패");
			return "/login";
		} else {
			// 값이 리턴되면 hSession에 세팅
			hSession.setAttribute("MEMVO", memberVO);
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession hSession) {
		hSession.removeAttribute("MEMVO");
		hSession = null;
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		return "/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO memVO,Model model) {
		
			memVO = mService.join(memVO);
		return "redirect:/";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/idcheck/{id}",method=RequestMethod.GET)
	public String idcheck(@PathVariable("id")  String id) {
		
		MemberVO memVO = mService.findById(id);
		if(memVO == null) {
			return "0";
		} else {
			return "1";
		}
	}
	
	// 마이페이지 수정 구현 정지
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(String us_id, Model model) {
		
		MemberVO memVO = mService.findById(us_id);
		model.addAttribute("MEM", memVO);
		
		return "/mypage";
	}
	
}
