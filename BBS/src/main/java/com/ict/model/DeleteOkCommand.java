package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;

public class DeleteOkCommand implements Command{
	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		String b_idx = request.getParameter("b_idx");
		
		
		// 관련 댓글 전체 삭제
		// 방법1. 원글에 속한 댓글 모두삭제 모삭
		int res = DAO.getCommentDeleteAll(b_idx);
		
		
		// 방법2. 오류페이지로 이동(퀴즈)
		
		// 방법3. 삭제된 게시글입니다로 변경 시키는 것(컬럼 추가) 
		
		//원 글 삭제
		DAO.getDelete("b_idx");
		return "MyController?cmd=list";
	}
}
