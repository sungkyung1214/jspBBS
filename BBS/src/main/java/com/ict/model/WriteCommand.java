package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteCommand implements Command {
	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		// 쓰는 화면으로 보내는거 그래서 그냥 이동만 하면 된다.
		
		
		
		return "view/write.jsp";
	}
}
