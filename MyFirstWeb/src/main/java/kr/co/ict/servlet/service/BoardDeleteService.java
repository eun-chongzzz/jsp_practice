package kr.co.ict.servlet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ict.boardDAO;

public class BoardDeleteService implements IBoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String strbNum = request.getParameter("board_num");
		int bNum = Integer.parseInt(strbNum);
		
		boardDAO dao = boardDAO.getInstance();
		
		dao.deleteBoard(bNum);
	}

}
