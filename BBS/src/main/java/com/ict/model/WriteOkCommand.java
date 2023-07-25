package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteOkCommand implements Command{
	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		// 파일 업로드를 포함할 수 도 있음
		try {
			String path = request.getServletContext().getRealPath("upload"); //실제 서블릿의 위치
			MultipartRequest mr= 
					new MultipartRequest(request,path, 100*1024*1024, "utf-8",
							new DefaultFileRenamePolicy());
			BVO bvo = new BVO();
			//bvo 사용하면 request대신 mr을 사용한다.
			bvo.setSubject(mr.getParameter("subject"));
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setContent(mr.getParameter("content"));
			bvo.setPwd(mr.getParameter("pwd"));
			
			// 첨부파일 처리
			if(mr.getFile("f_name") != null) {
				bvo.setF_name(mr.getFilesystemName("f_name"));
			}else {
				bvo.setF_name("");
			}
			
			int result = DAO.getInsert(bvo);
			if(result>0) {
				return "MyController?cmd=list";//리스트에 새로고침된거 보여주기 위해서 돌아가야한다
				//return을 해줘야 하는이유는 try문 안에있어서 try문밖에 있는 처리가 없어서
				//if 조건문에 안 걸리고 catch에 걸릴 수도 있어서
			}else {
				return "MyController?cmd=write";//삽입이 안되면 write로 다시 돌아간다.
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null; //혹시라도 오류로 떨어지면 널값을 보여준다
	}
}
