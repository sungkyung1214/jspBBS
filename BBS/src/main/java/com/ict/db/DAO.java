package com.ict.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

// DB처리하는 메서드들을 포함하고 있는 클래스

public class DAO {
	// 실제 사용하는 클래스 = SqlSession이다.
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재사용하는것.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
// =======================페이지 기법하면서  필요없어짐=======================	
//	// 리스트 가져오기
//	public static List<BVO> getList(){
//		//반환형이 List<BVO> 이다.
//		List<BVO> list = getSession().selectList("bbs.list");
//											//""에들어갈것은 메퍼에서 가져올 이름 정해서 넣기
//		return list;
//	} 
//	// =======================페이지 기법하면서  필요없어짐=======================	
	
	
	// 페이지 처리
	// 1. 전체 게시물의 수 구하기
	public static int getCount() {
		int result = getSession().selectOne("bbs.count");
		return result;
	}
	
	// 원글 리스트
	public static List<BVO> getList(int begin, int end){
		// select문에서  파라미터가 2개 이상이면 vo 또는 map을 사용해야 한다.
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin",begin);
		map.put("end", end);
		List<BVO> list =getSession().selectList("bbs.list",map);
		return list;
		
		
	}
	
	//=======================================================================================
	// 원글 삽입
	public static int getInsert(BVO bvo) {
		int result = getSession().insert("bbs.insert", bvo);
		ss.commit();
		return result;
	}
	
	// 원글 조회수
	public static int getHit(String b_idx) {
		int result = getSession().update("bbs.hit",b_idx);
		ss.commit();
		return result;
	}
	
	// 원글 상세보기
	public static BVO getOneList(String b_idx) {
		BVO bvo = getSession().selectOne("bbs.onelist",b_idx);
		return bvo;
	}
	
	// 원글 수정하기
	public static int getUpdate(BVO bvo) {
		int result = getSession().update("bbs.update",bvo);
		ss.commit();
		return result;
	}

	public static int getDelete(String b_idx) {	
		int result = getSession().delete("bbs.delete",b_idx);
		ss.commit();
		return result;
	}
	
	// 댓글 가져오기
	public static List<CVO> getClist(String b_idx){
		List<CVO> c_list =getSession().selectList("bbs.clist",b_idx);
		return c_list;
	}
	
	// 댓글 삽입
	public static int getC_Insert(CVO cvo) {
		int result = getSession().insert("bbs.cinsert",cvo);
		ss.commit();
		return result;
	}
	
	// 댓글 삭제
	public static int getC_delete(String c_idx) {
		int result = getSession().delete("bbs.cdelete",c_idx);
		ss.commit();
		return result;
	}
	
	// 원글 관력 모든 댓글 삭제
	public static int getCommentDeleteAll(String b_idx) {
		int result = getSession().delete("bbs.c_all_delete",b_idx);
		ss.commit();
		return result;
	}
}

