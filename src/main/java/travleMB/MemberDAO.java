package travleMB;

import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect {
	
	public MemberDAO(String driver, String url, String id, String pass) {
		super(driver, url, id, pass);
	} // MemberDAO 끝
	
	// application 내장객체를 인수로 전달한 후 DB 연결
	public MemberDAO(ServletContext application) {
		super(application);
	} // MemberDAO 끝
	
	/*
	 사용자가 입력한 아이디, 패스워드 확인 후 존재하는 정보일 경우 DTO 객체에 레코드를 담아 반환
	 */
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		
		String query = "SELECT * FROM travel_member WHERE id=? AND pass=?";
		
		try {
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			} // if 끝
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	} // MemberDTO 끝
}
