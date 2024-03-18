<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>travelix | 자유게시판 글쓰기</title>
<link rel="stylesheet" href="styles/skin.css">
<script>
function validateForm(form) {
    if (form.title.value == "") {
        alert("제목을 입력하세요.");
        form.title.focus();
        return false;
    }
    if (form.content.value == "") {
        alert("내용을 입력하세요.");
        form.content.focus();
        return false;
    }
    if (form.pass.value == "") {
        alert("비밀번호를 입력하세요.");
        form.pass.focus();
        return false;
    }
}
</script>
</head>
<body>
  <div class="write_wrap">
    <div class="inner">
      <h2>자유게시판 글쓰기</h2>
      
      <form name="writeFrm" enctype="multipart/form-data" 
      	method="post" action="fBoardWrite.do" onsubmit="return validateForm(this);">
	      <div class="write_form">
	      	<input type="text" name="name" />
	        <div class="title_wrap">
	          <span>제목</span>
	          <input class="title" type="text" name="title" />
	        </div>
	        <textarea class="content" name="content" >내용</textarea>
	        <div class="pass_wrap">
	    		<span>비밀번호</span>    
		        <input class="pass" type="password" name="pass" />
	        </div>
	      </div>
	      <div class="btn_wrap">
	        <button class="cancel_btn" type="button" onclick="location.href='fBoard.do';">취소</button>
	        <button class="fwrite_btn" type="submit">등록</button>
	      </div>
      </form>
    </div>
  </div>
</body>
</html>