<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="styles/skin.css">
  <title>Login</title>
</head>
<body>
  <div id="login_wrap">
    <div class="inner">
      <div class="login_box">
        <h2>Login</h2>
        
        <script>
        	// 로그인 폼의 입력값을 검증하기 위한 함수. 빈값인지 체크
        	function validateForm(form) {
        		// 부정연산자를 통해 빈값인지 판단
        		if (!form.user_id.value) {
        			alert("아이디를 입력하세요.");
        			// 포커스 이동하여 바로 입력할 수 있게끔
        			form.user_id.focus();
        			
        			return false;
        		}
        		
        		if (!form.user_pw.value) {
        			alert("패스워드를 입력하세요.");
        			form.user_pw.focus();
        			
        			return false;
        		}
        	}
        </script>
        
        <!-- 로그인 Form -->
        <form class="login_form" action="LoginProcess.jsp" method="post" name="loginFrm" 
        	onsubmit="return validateForm(this);">
          <div class="login">
            <div class="user_id">
              <span>Id</span>
              <input class="login_input" type="text" name="user_id" />
            </div>
            <div class="user_pw">
              <span>Pass</span>
              <input class="login_input" type="password" name="user_pw" />
            </div>
	        <div class="etc_txt">
		    	<a href="#">비밀번호를 잊으셨나요?</a>
	        </div>
          </div>
          <input class="login_btn" type="submit" value="로그인" />
          <a class="join_btn" href="#">회원가입</a>
        </form>
      </div>
    </div>
  </div>
</body>
</html>