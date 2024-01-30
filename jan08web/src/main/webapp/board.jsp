<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷티드 -->

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/board.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
table {
	width: 500px;
	height: 400px;
	border-collapse: collapse;
	/* display: flex; */
	justify-content: center;
	margin: auto;
}
tr:hover{
	border-bottom-color: rgb(165, 85, 85);
}
th{
	background-color: rgb(170, 136, 123);
	border-bottom: 1px solid brown;
}
td{
	border-bottom: 1px solid rgb(167, 134, 134);
	text-align: center;
}
.title{
	text-align: left;
}
.w1{width: 10%}
.w2{width: 20%}
footer{
	position: fixed;
	bottom: 0;
	width: 100%;
	height: 30px;
	background-color: rgb(185, 160, 150);
}
.title a{
	text-decoration: none;
}
.title a:visited{
	color: rgb(82, 49, 49);
}
.title a:link {
	color: rgb(110, 31, 31);
}
.title a:hover {
	color: brown;
}

.paging{
	margin: 0 auto;
	width: 500px;
	height: 30px;
	background-color: gray;
	margin-top: 10px;
	text-align: center;
	margin-left: auto;
  	margin-right: auto;
}

button{
	margin: 0 auto;
}

</style>
</head>
<body>

	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!-- 가져와서 같이 컴파일됨 -->
		</header>
		<div class="main">
			<div style="width: 800px; margin: 0 auto; padding-top: 10px">
				<%-- <article>
					for문 연습해보기<br>
					<c:forEach items="${list }" var="e" varStatus="s">
						${e.no } / ${s.first } / ${s.last } / ${s.index } / ${s.step }
						<br>
					</c:forEach>
				</article>
				<article>
					<fmt:requestEncoding value="UTF-8" />
					<fmt:setLocale value="ko_kr" />
					<fmt:formatNumber value="3.14" type="number" />
					<fmt:parseNumber value="3.14" integerOnly="true" />

					<c:set var="nowDate" value="<%=new Date()%>"></c:set>
					${nowDate } <br>
					<fmt:formatDate type="time" value="${nowDate }" />
					<br>
					<fmt:formatDate type="date" value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
						value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
						value="${nowDate }" />
					<br>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
						value="${nowDate }" />
					<br>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${nowDate }" />
				</article>--%> 
				<article>
					
					
					<c:choose>
						<c:when test="${fn:length(list) gt 0}">
							<table>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>날짜</th>
									<th>읽음</th>
									<th>아이피</th>
								</tr>
								<c:forEach items="${list }" var="row">
									<tr>
										<td class="w1">${row.no}</td>
										<td class="title">
											<a href="./detail?page=${page}&no=${row.no }">${row.title }
											<c:if test="${row.comment  ne 0}">
											<span>${row.comment }</span></c:if></a>
										</td>
										<td class="w2">${row.write }</td>
										<td class="w1">${row.date }</td>
										<td class="w1">${row.count }</td>
										<td class="w1">${row.ip }</td>
									</tr>
								</c:forEach>
							</table>
							전체 글 : ${totalCount } /
							페이지 수 : <c:set var="totalPage" value="${totalCount / 10 }"/>
							<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/>
							<c:if test="${totalCount % 10 gt 0 }">
								<c:set var="totalPage" value="${totalPage + 1 }"/>
							</c:if>
							<c:out value="${totalPage }"/>
							 / startPage : <c:set var="startPage" value="1"/> 
							 <c:if test="${page gt 5 }">
							 	<c:set var="startPage" value="${page - 5 }"/>${startPage }
							 </c:if>
							 / endPage : <c:set var="endPage" value="${startPage + 9 }"/> ${endPage }
							 <c:if test="${endPage gt totalPage }">
							 	<c:set var="endPage" value="${totalPage}"/>
							 	<c:set var="startPage" value="${totalPage - 9}"/>
							 </c:if>
							 / page : ${page }
							 
							<div class="paging">
								<button onclick="paging(1)">⏮️</button>
									<button	<c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if> onclick="paging(${page - 10})">⏪</button>
								<c:forEach begin="${startPage }" end="${endPage }" var="p">
									<button
									<c:if test="${page eq p }"> class="currentBtn"</c:if>
									onclick="paging(${p})">${p }</button>
								</c:forEach>
								<button
								
								<c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if>
								 onclick="paging(${page + 10})">⏩</button>
								<button onclick="paging(${totalPage})">⏭️</button>
							</div>
						</c:when>
						<c:otherwise>
							<h1>출력할 값이 없습니다.</h1>
						</c:otherwise>
					</c:choose>
					<c:if test="${sessionScope.mname ne null}">
					<button onclick="url('./write')">글쓰기</button>
					</c:if>
					<!-- ****** 윗 줄 외워라 *******-->
				</article>
			</div>
		</div>
		<footer><c:import url="footer.jsp"/>
		</footer>

	</div>
	<script type="text/javascript">
		function paging(no){
			location.href="./board?page="+no;
		}
	</script>
	

</body>
</html>