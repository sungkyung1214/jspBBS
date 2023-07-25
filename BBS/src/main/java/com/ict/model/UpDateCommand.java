package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;

public class UpDateCommand implements Command{
	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		String b_idx = request.getParameter("b_idx");
		String cPage = request.getParameter("cpage");
		BVO bvo = DAO.getOneList(b_idx);
		
		if(bvo != null) {
			request.setAttribute("bvo", bvo);
			request.setAttribute("cPage", cPage);
			return "view/update.jsp";
		}else {
			return "MyController?cmd=onelist&b_idx="+b_idx;
			
		}
	}
}
