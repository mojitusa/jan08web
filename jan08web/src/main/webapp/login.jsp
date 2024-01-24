<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"><</script>

	<style type="text/css">
		/* button[type="submit"]{
		font-size:20px;
		padding:10px 20px;
		width: 50%
		}
		
		button[type="reset"]{
		font-size:20px;
		padding:10px 20px;
		width: 50%
		} */
		
		.login input{
		width: 30%;
		height: 50px;
		margin: 0px 17.5px;
		font-size: 20px
		}
		
		.login button{
		width: 30%;
		font-size:20px;
		padding:10px;
		margin: 10px 20px 0px 20px;
		}
		
	</style>
	<script type="text/javascript">
		function err(){
			let errBox = document.querySelector("#errorMSG");
			errBox.innerHTML = "<marquee>올바른 ID와 PW를 입력하세요.</marquee>";
			errBox.style.color = 'red';
			errBox.style.fontSize = "10pt";
		}
	</script>
</head>
<body>
	<header>
		<%@ include file="menu.jsp" %>
	</header>
	<div class="main">
		<div>
			<article>
				<h1>로그인 페이지입니다.</h1>
				<c:if test="${param.error ne null}">
					<script type="text/javascript">
						alert("아이디와 비밀번호가 맞지 않습니다.");
						var errorBox = document.getElementById("errorMSG");
						errorBox.innerHTML = "올바른 암호와 아이디를 입력하세요.";
					</script>
				</c:if>
				<!-- 서버에서 처리 후 보냄 그래서 html 소스에 안 보임 -->
				
				<div class="login">
					<form action="./login" method="post">
						<input type="text" name="id" placeholder="아이디"><br>
						<input type="password" name="pw" placeholder="비밀번호"><br>
						<button type="submit">로그인</button><br>
						<button type="reset">초기화</button>
						<div id=errorMSG"></div>
					</form>	
					<a href="./join">회원가입</a> 
				</div>
				 
			</article>
			<article>
				
				<br>
				<c:forEach begin="1" end="10" var="row" varStatus="s">
					${s.begin } / ${s.end } / 
					<!-- 외워라 몸에 익혀라 -->
					
				</c:forEach>
				
			</article>
			<article>
			</article>
		</div>
	</div>
	<c:if test="${param.error ne null }">
		<script type="text/javascript">
			err();
		</script>
	</c:if>
</body>
</html>