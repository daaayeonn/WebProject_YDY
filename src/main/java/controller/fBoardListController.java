package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.BoardPages;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import travelBoard.FBoardDAO;
import travelBoard.FBoardDTO;

public class fBoardListController extends HttpServlet {
	
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
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.selectCount(map);
		
		ServletContext application = getServletContext();
		
		// 한 페이지에 출력할 갯수
		int pageSize = 
        		Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        //한 블럭당 출력할 페이지번호의 갯수
        int blockPage = 
        		Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
        
        // 현재 페이지 확인
        int pageNum = 1;
        String pageTemp = req.getParameter("pageNum");
        
        if (pageTemp != null && !pageTemp.equals("")) {
        	pageNum = Integer.parseInt(pageTemp);
        }
        
        int start = (pageNum - 1) * pageSize + 1;
        int end = pageNum * pageSize;
        
        map.put("start", start);
        map.put("end", end);
		
		List<FBoardDTO> boardLists = dao.listPage(map);
		
		//dao.close();
		
		String paging = BoardPages.paging(totalCount, pageSize, blockPage, pageNum, "fBoard.do");
		
		map.put("paging", paging); // 출력할 페이지 번호
		map.put("totalCount", totalCount); // 게시물의 전체 갯수
		map.put("pageSize", pageSize); // 페이지당 출력 갯수
		map.put("pageNum", pageNum); // 현재 페이지 번호
		
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("community.jsp").forward(req, resp);
	}
}
