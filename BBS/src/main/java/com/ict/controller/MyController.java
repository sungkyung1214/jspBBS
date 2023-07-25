package com.ict.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.model.Command;
import com.ict.model.Comment_Delete;
import com.ict.model.Comment_Write;
import com.ict.model.DeleteCommand;
import com.ict.model.DeleteOkCommand;
import com.ict.model.ListCommand;
import com.ict.model.OneListCommand;
import com.ict.model.UpDateCommand;
import com.ict.model.UpdateOkCommand;
import com.ict.model.WriteCommand;
import com.ict.model.WriteOkCommand;

@WebServlet("/MyController")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out =response.getWriter();
		
		String cmd= request.getParameter("cmd");
		Command comm =null;
		if(cmd.equals("list")) {
			comm = new ListCommand();
		}else if(cmd.equals("write")) {
			comm = new WriteCommand();
		}else if(cmd.equals("write_ok")) {
			comm = new WriteOkCommand();
		}else if(cmd.equals("onelist")) {
			comm = new OneListCommand();
		}else if(cmd.equals("update")) {
			comm = new UpDateCommand();
		}else if(cmd.equals("delete")) {
			comm = new DeleteCommand();
		}else if(cmd.equals("update_ok")) {
			comm = new UpdateOkCommand();
		}else if(cmd.equals("delete_ok")) {
			comm = new DeleteOkCommand();
		}else if(cmd.equals("c_write")) {
			comm = new Comment_Write();
		}else if(cmd.equals("c_delete")) {
			comm = new Comment_Delete();
		}
		String path =comm.exex(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}
}
