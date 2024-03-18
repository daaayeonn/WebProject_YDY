package common;

public class BoardPages {

	public static String paging(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		
		String paging = "";
		
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		// 첫번째 페이지만 제외하고 링크 화면에 출력
		if (pageTemp != 1) {
			paging += "<a href='" + reqUrl + "?pageNum=1'>[첫페이지]</a>";
			paging += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "'>[이전블록]</a>";
		}
		
		int blockCount = 1;
		
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				paging += "<span class='page_temp'>" + pageTemp + "</span>";
			} else {
				paging += "<a class='page_btn' href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>";
			}
			
			pageTemp++;
			blockCount++;
		}
		
		// 마지막 페이지만 제외하고 바로가기 링크 화면에 출력
		if (pageTemp <= totalPages) {
			paging += "<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>[다음 블록]</a>";
			paging += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'>[마지막 페이지]</a>";
		}
		
		return paging;
	}
}
