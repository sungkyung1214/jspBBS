package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.CVO;
import com.ict.db.DAO;

public class Comment_Write implements Command{
	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		String b_idx = request.getParameter("b_idx");
		String writer = request.getParameter("writer");
		String content =request.getParameter("content");
		
		CVO cvo = new CVO();
		cvo.setB_idx(b_idx);
		cvo.setWriter(writer);
		cvo.setContent(content);
		
		int result = DAO.getC_Insert(cvo);
		return "MyController?cmd=onelist&b_idx="+b_idx;
	}
}
