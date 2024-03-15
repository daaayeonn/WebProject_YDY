package travelBoard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import jakarta.servlet.ServletContext;
import travleMB.JDBConnect;

public class FBoardDAO extends JDBConnect {
	
	public FBoardDAO(String driver, String url, String id, String pass) {
		super(driver, url, id, pass);
	}
	
	public FBoardDAO(ServletContext application) {
		super(application);
	}
	
	// 게시물 갯수 카운트
	public int selectCount(Map<String, Object> map) {
		
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM travel_Fboard";
		
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(query);
			rs.next();
			
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// 목록에 출력할 게시물 인출
	public List<FBoardDTO> listPage(Map<String, Object> map) {
		
		List<FBoardDTO> fboard = new Vector<FBoardDTO>();
		
		String query = "SELECT * FROM ( "
						+ " SELECT Tb.*, ROWNUM rNum FROM ( "
						+ " SELECT * FROM travel_Fboard ";
		
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		// 정렬
		query += " ORDER BY idx DESC "
				+ " ) Tb"
				+ " ) "
				+ " WHERE rNum BETWEEN ? AND ?";
		
		try {
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				FBoardDTO dto = new FBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setPass(rs.getString(6));
				dto.setVisitcount(rs.getInt(7));
				
				fboard.add(dto);
			} // while 끝
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return fboard;
	} // listPage 끝
}
