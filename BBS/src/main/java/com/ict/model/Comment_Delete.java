package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.CVO;
import com.ict.db.DAO;

public class Comment_Delete implements Command{
	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		String c_idx = request.getParameter("c_idx");
		String b_idx = request.getParameter("b_idx");
		String cPage = request.getParameter("cPage");
		int result = DAO.getC_delete(c_idx);
		
		
		// TODO Auto-generated method stub
		return "MyController?cmd=onelist&b_idx="+b_idx;
	}
}
