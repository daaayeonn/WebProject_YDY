<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/skin.css">
<title>travelix | 회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	// 비밀번호 맞는지 검증
	$(function() {
		$('#pw_true').hide();
		$('#pw_false').hide();
		
		$('#user_pw_ch').keyup(function() {
			var pw = $('#user_pw').val();
			var pwCh = $('#user_pw_ch').val();
			
			if(pw === pwCh) {
				$('#pw_true').show();
				$('#pw_false').hide();
			}
			else {
				$('#pw_false').show();
				$('#pw_true').hide();
			}
		});
	});

	// 로그인 폼의 입력값을 검증하기 위한 함수. 빈값인지 체크
	function validateForm(form) {
		if (!form.user_name.value) {
			alert("아이디를 입력하세요.");
			// 포커스 이동하여 바로 입력할 수 있게끔
			form.user_name.focus();
			
			return false;
		}
		
		if (!form.user_id.value) {
			alert("아이디를 입력하세요.");
			// 포커스 이동하여 바로 입력할 수 있게끔
			form.user_id.focus();
			
			return false;
		}
		
		if (!form.user_pw.value) {
			alert("비밀번호를 입력하세요.");
			form.user_pw.focus();
			
			return false;
		}
		
		if (!form.user_pw_ch.value) {
			alert("비밀번호를 확인하세요.");
			form.user_pw_ch.focus();
			
			return false;
		}
	}
</script>
</head>
<body>
	<div id="register_wrap">
	    <div class="inner">
	      <div class="register_box">
	        <h2>회원가입을 위해<br>정보를 입력해주세요.</h2>
	        <form class="register_form" action="register.do" method="post" name="registerFrm" 
	        	onsubmit="return validateForm(this);">
	          <div class="register">
	            <div class="user_name">
	              <span>이름 <strong>*</strong></span>
	              <input class="register_input" type="text" name="user_name" />
	            </div>
	            <div class="user_id">
	              <span>아이디 <strong>*</strong></span>
	              <input class="register_input" type="text" name="user_id" />
	            </div>
	            <div class="user_pw">
	              <span>비밀번호 <strong>*</strong></span>
	              <input class="register_input" type="password" name="user_pw" id="user_pw" />
	            </div>
	            <div class="user_pw_ch">
	              <span>비밀번호 확인 <strong>*</strong><em id="pw_true">비밀번호가 일치합니다.</em><em id="pw_false">비밀번호가 일치하지않습니다.</em></span>
	              <input class="register_input" type="password" name="user_pw_ch" id="user_pw_ch" />
	            </div>
	          </div>
	          <input class="register_btn" type="submit" value="회원가입" />
	        </form>
	      </div>
	    </div>
 	</div>
</body>
</html>