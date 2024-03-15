package common;

public class BoardPages {

	public static String paging(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl) {
		
		String paging = "";
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
		
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		if (pageTemp != 1) {
			paging += "<a href='" + reqUrl + "?pageNum = 1'><img src='images/arrow_next1 alt='다음페이지' /></a>";
			paging += "<a href='" + reqUrl + "?pageNum =" + (pageTemp -1) +"'><img src='images/arrow_next2 alt='마지막페이지' /></a>";
		}
		
		int blockCount = 1;
		
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			
			if (pageTemp == pageNum) {
				paging += "&nbsp;" + pageTemp + "&nbsp;";
			} else {
				paging += "&nbsp; <a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + 
						pageTemp + "</a>&nbsp;";
			}
			
			// 1씩 증가시켜 순차적으로 페이지 번호 출력
			pageTemp++;
			blockCount++;
		} // while 끝
		
		if (pageTemp <= totalPages) {
			paging += "<a href='" + reqUrl + "?pageNum =" + pageTemp + "'><img src='images/arrow_next1 alt='다음페이지' /></a>";
			paging += "<a href='" + reqUrl + "?pageNum =" + totalPages +"'><img src='images/arrow_next2 alt='마지막페이지' /></a>";
		}
		
		return paging;
	}
}
