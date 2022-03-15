package kr.co.ict.servlet.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}
}
