package kr.co.ict.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ict.boardDAO;
import kr.co.ict.boardVO;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strpNum = request.getParameter("pageNum");
		int pNum = 0;
		try {
			pNum = Integer.parseInt(strpNum);
		} catch(Exception e) {
			pNum =1;
		}
		
		
		// 1. 접속시 boardDAO생성
		boardDAO dao = boardDAO.getInstance();
		// 2. boardDAO의 getAllBoardList() 호출해 전체 게시물 목록 받아오기
		List<boardVO> boardList = dao.getAllBoardList(pNum);
		// 3. request.setAttribute로 바인딩하기
		request.setAttribute("boardList", boardList);
		// 4. /board/boardlist.jsp로 포워딩하기
		RequestDispatcher dp = request.getRequestDispatcher("/board/boardlist.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
