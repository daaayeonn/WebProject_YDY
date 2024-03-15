package controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import travleMB.MemberDAO;
import travleMB.MemberDTO;

public class registerController extends HttpServlet {
	
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		MemberDTO dto = new MemberDTO();
		
		dto.setName(req.getParameter("user_name"));
		dto.setId(req.getParameter("user_id"));
		dto.setPass(req.getParameter("user_pw"));
		
		int result = dao.insertMember(dto);
		dao.close();
		
		if (result == 1) {
			resp.sendRedirect("login.do");
		}
		else {
			System.out.println("회원가입 실패");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
	}
}
