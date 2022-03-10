package kr.co.ict;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class boardDAO {
	
	// DAO 코드 재활용하기
	// 1.UserDAO에서 주석처리가 안된 부분만
	// getInstance() 메서드까지 가져옴
	
	// 2. ~~~DAO로 되어있는 부분을 전부 현재 DAO클래스명으로 변경
	// 예) UserDAO -> BoardDAO로 전환중이므로 UserDAO라고 적힌 부분을 전부 BoardDAO로 변경
	private DataSource ds = null;
	
	private static boardDAO dao = new boardDAO();

	private boardDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boardDAO getInstance() {
		if(dao == null) {
			dao = new boardDAO();
		}
		return dao;
	}
	
	// 3. 필요로 하는 로직과 유사한 메서드를 복사해옵니다.
	// 게시판 글 전체 목록가져오기 -> 회원 전체 목록 가져오기를 이용해 수정
	// 회원 전체 목록을 가져오는 getAllUserList를 수정해 getAllBoardList()를 생성해보겠습니다.
	
	// UserVO는 userInfo테이블에 맞춰서 생성된 것이므로
	// boardTbl에 맞춰서 처리하기 위해 UserVO를 사용하는 부분을 전부 BoardVO로 수정해줍니다.
	
	// 3-2. 쿼리문을 boardTbl 테이블에서 데이터를 가져오도록 수정합니다.
	
	// 3-3. while문 내부에서 BoardVO 세팅이 가능하도록 rs에서 데이터를 가져오는 부분을 수정합니다.
	public List<boardVO> getAllBoardList(){
		// try블럭 진입전 Connection, PreparedStatement, ResultSet을 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// try블럭 진입 전에 ArrayList 선언
		List<boardVO> boardList = new ArrayList<>();
		try {
		// Connection, PreparedStatement, ResultSet을 선언합니다.
			con = ds.getConnection();
		
		// SELECT * FROM userinfo 실행 및 ResultSet에 저장
		 String sql = "SELECT * FROM boardTbl ORDER BY board_num DESC";
		 pstmt = con.prepareStatement(sql);	
		
		 rs = pstmt.executeQuery();
		
		// boardVO ArrayList에 rs에 든 모든 자료를 저장해주세요.
		 while(rs.next()) {
			 // 7개 컬럼 값을 받아서 변수로 저장받으시고
			 int board_num = rs.getInt("board_num");
			 String title = rs.getString("title");
			 String content = rs.getString("content");
			 String writer = rs.getString("writer");
			 Date bDate = rs.getDate("bdate");
			 Date mDate = rs.getDate("mdate");
			 int hit = rs.getInt("hit");
			 ///int board_num, String title, String content, String writer, Date bDate, Date mDate, int hit
			 // 해당 변수를 이용해 BoardVO를 생성해주시면 됩니다.
			 boardVO boardData = new boardVO(board_num, title, content, writer, bDate, mDate, hit);
			 boardList.add(boardData);
		 }
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
			} catch(SQLException es) {
				es.printStackTrace();
			}
		}
		return boardList;
	}
	
	// insertBoard 내부 쿼리문 실행시 필요한 3개 요소인 글제목, 본문, 글쓴이를 입력해야하만 실행할 수 있게 설계합니다.
	public void insertBoard(String title, String content, String writer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			// INSERT의 경우 두 가지 유형이 있음
			// 전체 컬럼 요소 다 넣기 - INSERT INTO boardTbl VALUES (null, ?, ?, ?, now(), now(), 0);
			// 일부요소만 넣기 - INSERT INTO boardTbl(title, content, writer) Values (?,?,?);
			String sql = "INSERT INTO boardTbl(title, content, writer) Values (?,?,?)";
			pstmt = con.prepareStatement(sql);
			// 실행 전 상단 쿼리문 ? 채워넣기
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boardVO getBoardDetail(int board_num) {
		// DB연동구문을 작성해보세요.
		// try구문 초입에 ds부분까지만 하셔도 되는데 만약 다 작성 가능하다면 다 작성해보세요.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boardVO board = null;
		
		try {
			con = ds.getConnection();
			
			String sql = "SELECT * FROM boardTbl WHERE board_num = ?"; 
			pstmt = con.prepareStatement(sql); 
			pstmt.setInt(1, board_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				int boardNum = rs.getInt("board_num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				Date bDate = rs.getDate("bdate");
				Date mDate = rs.getDate("mdate");
				int hit = rs.getInt("hit");
				
				board = new boardVO(boardNum, title, content, writer, bDate, mDate, hit);
			}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();	
					pstmt.close();
					rs.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return board; 
	}
	
	// deleteBoard 메서드를 만들어서 삭제처리가 되게 만들어주시고
	// 서블릿에서 해당 메서드를 호출해 실제로 삭제버튼을 누르면 DB에서 해당번호글이 삭제되게 해주세요.
	public void deleteBoard(int boardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			String sql = "DELETE FROM boardTbl WHERE board_num = ?";
			pstmt = con.prepareStatement(sql);
		
			pstmt.setInt(1, boardNum);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateBoard(String title, String content, int boardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			String sql = "UPDATE boardTbl SET title=?, content=?, mdate=now() WHERE board_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNum);
		
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}