package com.callor.jdbc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.model.UserVO;
import com.callor.jdbc.persistance.AuthorDao;
import com.callor.jdbc.service.AuthorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/author")
public class AuthorController {
	
	protected final AuthorService auService;
	public AuthorController(AuthorService auService) {
		// TODO Auto-generated constructor stub
		this.auService = auService;
	}
	
	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String list(HttpSession hSession, Model model) {
		
		UserVO userVO = (UserVO)hSession.getAttribute("USERVO");
		if(userVO == null) {
			model.addAttribute("MSG", "LOGIN");
			return "redirect:/member/login";
			
		}
		List<AuthorVO> auList = auService.selectAll();
		model.addAttribute("AUTHORS", auList);
		log.debug(auList.toString());
		return "author/list";
	}
	
	/*
	 * cp_title을 Req로 부터 받아 변수에 세팅을 하는데
	 * Req를 할 때 cp_title 변수르 보내지 않으면
	 * 400번대 httpStatuse 오류가 발생한다
	 * 400 오류는 서버 App 디버깅 과정에서 상당히 관리하기 어려운 오류가 된다
	 * 
	 * 단순한 변수(VO, DTO, MAP 형식이 아닌 단일 변수)일 경우는
	 * @RequestParam의 required 옵션을 false로 선언하고
	 * default 값을 임의로 설정해 두면 코드 내에서 핸들을 할 수 있다
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(@RequestParam(name = "au_name", required = false, defaultValue = "")String au_name, Model model) {
	
		if(au_name == null || au_name.equals("")) {
			List<AuthorVO> auList = auService.selectAll();
			model.addAttribute("AUTHORS", auList);
		} else {
			List<AuthorVO> auList = auService.findByNameAndTel(au_name);
			model.addAttribute("AUTHORS", auList);
		}
		
		
		return "author/search";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		
		return "author/input";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update() {
		
		
		return "author/input";
	}
}
