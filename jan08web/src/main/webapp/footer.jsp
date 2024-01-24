<%@page import="com.ryuuzakiumi.util.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style type="text/css">
footer{
	position: fixed;
	bottom: 0;
	width: 100%;
	height: 30px;
	background-color: rgb(185, 160, 150);
}


</style>

	여기는 푸터입니다. 회사 정보 / 정보보안 책임자 / 연락처 / 주소

	당신의 아이피 : <%=Util.getIP(request)%>