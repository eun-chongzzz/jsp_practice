package kr.co.ict.servlet.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ict.boardDAO;
import kr.co.ict.boardVO;

public class BoardDetailService implements IBoardService {
	
	
	@Override
	public void execute (HttpServletRequest request, HttpServletResponse response) {
		
		String sbNum = request.getParameter("board_num");
		int bNum = Integer.parseInt(sbNum);
		//System.out.println(bNum);
		
		boardDAO dao =  boardDAO.getInstance();
		// dao에서 해당 글번호에 대한 정보를 가져오고
		boardVO board = dao.getBoardDetail(bNum);
		System.out.println(board);
		// 데이터 바인딩
		request.setAttribute("board", board);
		
		HttpSession session = request.getSession();
		// 2. .jsp의 스크립트릿 내부와 동일하게 세션값 얻어오기 가능(setAttribute로 저장 가능)
		String sId = (String)session.getAttribute("session_id");
		
		// 세션값 체킹이 확인되면 바인딩해서 포워딩시 세션값이 같이 넘어가도록 처리
		request.setAttribute("sId", sId);
	}
}
