package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public String exex(HttpServletRequest request, HttpServletResponse response);
	
}
