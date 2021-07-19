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
		memDao.update(memberVO);
		return memberVO;
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
		MemberVO findVO = memDao.findById(memberVO.getUs_id());
		if (findVO == null) {
			model.addAttribute("MSG", "NOT_USERID");
			return null;
		}
		if (findVO.getUs_pw().equals(memberVO.getUs_pw())) {
			return findVO;
		}
		model.addAttribute("MSG", "NEQ_PASS");
		return null;
	}

}