package kr.co.ict.servlet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ict.boardDAO;

public class BoardUpdateService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bNum = Integer.parseInt(request.getParameter("board_num"));
		
			boardDAO dao =  boardDAO.getInstance();
			
			dao.updateBoard(title, content, bNum);
	
	}
}
