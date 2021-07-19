package com.callor.woo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.woo.dao.MemberDao;
import com.callor.woo.model.MemberVO;
import com.callor.woo.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImplV1 implements MemberService {
	
	protected final MemberDao memDao;
	@Override
	public MemberVO join(MemberVO memberVO) {
		//회원 레벨 설정시 List 사용
		//List<MemberVO> members = memDao.selectAll();
		memDao.insert(memberVO);
		return memberVO;
	}
	
	
	// 마이페이지 수정시
	@Override
	public MemberVO update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public MemberVO findById(String id) {
		// TODO Auto-generated method stub
		MemberVO memberVO = memDao.findById(id.trim());
		return memberVO;
	}

	@Override
	public MemberVO login(MemberVO memberVO, Model model) {
		// TODO Auto-generated method stub
		MemberVO findVO = memDao.findById(memberVO.getUs_id()); // 사전에 아이디가 있는지 파악후
		if(findVO == null ) { // 없으면 
			model.addAttribute("MSG","NOT_USERID"); // 없는 아이디 메세지 전송
			return null; // Controller에 null값 리턴
		}
		if(findVO.getUs_pw().equals(memberVO.getUs_pw())) { // findById에 값이있어 findVO에 데이터가 담긴 값과, 페이지에서 전송된 pw 데이터값이 같으면 리턴VO
			return findVO;
		}
		model.addAttribute("MSG", "NEQ_PASS"); // pw equals 하는 if문에서 return되지 않을경우 패스워드가 틀린것임으로 null값 리턴
		return null;
	}

}