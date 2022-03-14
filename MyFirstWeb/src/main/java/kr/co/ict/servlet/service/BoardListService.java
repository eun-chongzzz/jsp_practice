package kr.co.ict.servlet.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ict.boardDAO;
import kr.co.ict.boardVO;

public class BoardListService implements IBoardService{
	
	// BoardListServlet에서 바인딩까지 복사해오면 됩니다.
	@Override
	public void execute (HttpServletRequest request, HttpServletResponse response) {
		boardDAO dao = boardDAO.getInstance();
		// 2. boardDAO의 getAllBoardList() 호출해 전체 게시물 목록 받아오기
		List<boardVO> boardList = dao.getAllBoardList();
		// 3. request.setAttribute로 바인딩하기
		request.setAttribute("boardList", boardList);
	}
}
