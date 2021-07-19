package com.callor.woo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.woo.dao.MemberDao;
import com.callor.woo.model.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	protected final MemberDao mDao;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(@RequestParam(name = "MSG",required = false) String msg, Model model) {
		
		if(msg == null) {
			model.addAttribute("MSG");
		} else if(msg.equals("LOGIN")) {
			model.addAttribute("MSG","NONE");
		} else if(msg.equals(model)) {
			model.addAttribute("MSG","로그인에 실패하셨습니다 다시 시도해주세요");
		}
		
		return "/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(String us_id, String us_pw,HttpSession hSession,Model model) {
		log.debug("id : {}", us_id);
		log.debug("pw : {}", us_pw);
		MemberVO memVO = mDao.login(us_id, us_pw);
		
		if(memVO == null ) {
			model.addAttribute("MSG","LOGIN_FAIL");
			return "redirect:/login";
		}
		//log.debug("확인 : {}" + memVO.toString());
		hSession.setAttribute("MEMVO", memVO);
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession hSession) {
		hSession.removeAttribute("MEMVO");
		hSession = null;
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinGet(Model model) {
		
		return "/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPost(MemberVO memVO,Model model) {
		
		try {
			mDao.insert(memVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "/join";
		}
		
		return "redirect:/";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/idcheck/{id}",method=RequestMethod.GET)
	public String idcheck(@PathVariable("id")  String id) {
		
		// selectAll 하기
		MemberVO memVO = mDao.findById(id);
		if(memVO == null) {
			return "0";
		} else {
			return "1";
		}
	}
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(String us_id) {
		
		return "mypage";
	}
	
}
