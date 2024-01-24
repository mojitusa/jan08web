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
</head>
<body>
	<header>
		<%@ include file="menu.jsp" %>
	</header>
	<div class="main">
		<div class="mainStyle" >
			<article>
				
				<%-- <c:set var="numbontent(rs.getString("board_title"));er" value="105"/> --%>
				<c:out value="${number }"/><br>
				
				<c:forEach begin="1" end="9" var="row" step="2">
					2 x ${row } = ${row * 2 }<br>
				</c:forEach>
				
				<c:if test="${number eq 105 }">
				number는 105입니다.<br>
				eq 11 == 5 같아? false
				ne 11 != 5 같지 않아? true
				lt 11 〈 5 작아? false
				gt 11 〉 5 커? true
				le 11 〈= 5	작거나 같아? false	
				ge 11 〉= 5 크거나 같아? true
				&&
				||
				empty
				not empty
				</c:if>
				<hr>
				1~45까지 짝수만 출력하세요.<br>
				
				<c:forEach begin="2" end="45" var="en" step="2">
					${en }
				</c:forEach>
				<br>
				<c:forEach begin="1" end="45" var="en">
					<c:if test="${en % 2 eq 0 }">
						${en }
					</c:if>
				</c:forEach>
				
				 
			</article>
			<article>
				<%-- <c:redirect url="./board"/> --%>
				<!-- 특정 페이지로 이동 조건문과 함께 쓰면 좋다 -->
				
				<br>
				<c:forEach begin="1" end="10" var="row" varStatus="s">
					${s.begin } / ${s.end } / 
					<!-- 외워라 몸에 익혀라 -->
					
				</c:forEach>
				
			</article>
			<article>
			</article>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
</body>
</html>