package controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import travleMB.MemberDAO;
import travleMB.MemberDTO;

public class LoginController extends HttpServlet {
	
	MemberDAO dao;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		
		String oracleDriver = application.getInitParameter("OracleDriver");
		String oracleUrl = application.getInitParameter("OracleURL");
		String oracleId = application.getInitParameter("OracleId");
		String oraclePwd = application.getInitParameter("OraclePwd");
		
		dao = new MemberDAO(oracleDriver, oracleUrl, oracleId, oraclePwd);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pw");
		
		MemberDTO memberDTO = dao.getMemberDTO(userId, userPw);
		
		if (memberDTO.getId() != null) {
			req.setAttribute("UserId", memberDTO.getId());
			req.setAttribute("UserName", memberDTO.getName());
			
			resp.sendRedirect("index.jsp");
		}
		else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
//			throws ServletException, IOException {
//		String userId = req.getParameter("user_id");
//		String userPw = req.getParameter("user_pw");
//		
//		MemberDTO memberDTO = dao.getMemberDTO(userId, userPw);
//		
//		if (memberDTO.getId() != null) {
//			req.setAttribute("UserId", memberDTO.getId());
//			req.setAttribute("UserName", memberDTO.getName());
//			
//			resp.sendRedirect("index.jsp");
//		}
//		else {
//			req.getRequestDispatcher("login.jsp").forward(req, resp);
//		}
//	}
	
	private void destory() {
		dao.close();
	}
}
