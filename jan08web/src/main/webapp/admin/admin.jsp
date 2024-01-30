<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.14" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
	<!-- 2024-01-26 관리자 페이지 만들기 -->
	<!-- 틀 -->
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menu.jsp" %>
		<!-- 본문 내용 -->
		<div class="main">
			이 페이지에 오는 모든 사람은 관리자인지 검사를 합니다.
			관리자일 경우에만 보여 주고 다른 경우에는 로그인 페이지로 보냅니다.
			<article>
			
				<div class="info">
					왼쪽 메뉴를 선택하세요.
				</div>
				
			</article> 
		</div>
	</div>

</body>
</html>