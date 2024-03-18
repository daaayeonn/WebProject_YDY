package controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import travelBoard.FBoardDAO;
import travelBoard.FBoardDTO;

public class fBoardWriteController extends HttpServlet {
	
	FBoardDAO dao;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		
		String oracleDriver = application.getInitParameter("OracleDriver");
		String oracleUrl = application.getInitParameter("OracleURL");
		String oracleId = application.getInitParameter("OracleId");
		String oraclePwd = application.getInitParameter("OraclePwd");
		
		dao = new FBoardDAO(oracleDriver, oracleUrl, oracleId, oraclePwd);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.getRequestDispatcher("fBoardWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		FBoardDTO dto = new FBoardDTO();
		
		dto.setName(req.getParameter("name"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setPass(req.getParameter("pass"));
		
		int result = dao.insertWrite(dto);
		// dao.close();
		
		if (result == 1) {
			resp.sendRedirect("fBoard.do");
		}
		else {
			System.out.println("글쓰기 실패");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
