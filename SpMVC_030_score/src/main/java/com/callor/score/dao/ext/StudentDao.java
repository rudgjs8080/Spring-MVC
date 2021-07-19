package com.callor.score.dao.ext;

import com.callor.score.dao.GenericDao;
import com.callor.score.model.StudentVO;

public interface StudentDao extends GenericDao<StudentVO, String>{

	// 학생테이블에 저장된 데이터에서
	// 가장 마지막의 학번(가장 큰 번호)를 추출하기
	
	// mapper 에서 사용해야 하기 때문에 이름이 중요함
	public String getMaxStNum();
	
}
