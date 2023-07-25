package com.ict.model;

import java.awt.Paint;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;
import com.ict.page.Paging;

public class ListCommand implements Command{

	@Override
	public String exex(HttpServletRequest request, HttpServletResponse response) {
		
	// =========================================================================
		//여기는 페이징기법 하기전에 필요했던 코드
		
		// 일반 리스트 가져오기
		// DB가서 리스트 가져오기
		//List<BVO> list = DAO.getList();
		
		// 저장
		//request.setAttribute("list", list);//위에 갈색 list에 디비에서 가져온 내용이 담겨있는데 이걸
											// cmd=list인 거에 담아줄려고하는 내용
		
		
	// =========================================================================	
		
		
		// 페이지 처리
		Paging paging = new Paging();
		
		
		// 1. 전체 게시물의 수
		int count = DAO.getCount();
		paging.setTotalRecord(count);
		
		// 2. 전페 페이지의 수
		// 전체 게시글의 수가 한 페이지안에 존재하는 원글의 수 보다 작거나 같으면 전체 페이지 수는 1페이지
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else{
			paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerPage());
			// 나머지가 있으면 1페이지 증가
			if(paging.getTotalRecord()/paging.getNumPerPage() != 0 ) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		
		// 3. 현재 페이지 구하기
		// cmd가 list이면 무조건 cPage라는 현재페이지값을 가지고 와야한다.
		// cPager가 nowPage로 변경 된다.
		String cPage = request.getParameter("cPage");
		
		// 맨 처음오면 cPage는 null이다.
		if(cPage == null) {
			paging.setNowPage(1); //null값이면 페이지 1로 세팅하겠다.
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// **4-1. 현재 페이지의 시작번호과 끝번호 구하기
		// 공식들어간다 입벌려~~~~
		paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage()+1);
		paging.setEnd((paging.getBegin()-1)+paging.getNumPerPage());
		
		// **4-2. 시작번호와 끝번호를 가지고 DB에서 원하는 만큼의 게시물을 가져온다.
		List<BVO> list = DAO.getList(paging.getBegin(),paging.getEnd());
		request.setAttribute("list", list);
		
		// ** 5. 현재 페이지의 시작 블록과 끝 블록을 구하자
		paging.setBeginBlock((int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock()+paging.getPagePerBlock()-1);
	
		// 주의사항 : endBlock이 totalPage보다 클 수 가 있다.
		//			사용하지 않는 페이지번호가 나오므로
		//			endBlock이 totalPage보다 크면
		//			endBlock를 totalPage로 변경 시키자
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		request.setAttribute("paging", paging);
		return "view/list.jsp";
	}

}
