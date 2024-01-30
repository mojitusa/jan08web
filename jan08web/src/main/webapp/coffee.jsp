<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<style>
	table {
		border-collapse: collapse;
		width: 300px;
	}
	
	td {
		
		border: 1px solid white;
	}
</style>
<body>
	<header>
		<%@ include file ="menu.jsp"%>
	</header>
		출력 되나요?<br><br>

	<form id ="selectCoffeeOrTea" action="./coffee" method="post" onsubmit="return handleSubmit()">
		<input type="radio" name="hotorcold" value="뜨거운"> 뜨거운 <br>
		<input type="radio" name="hotorcold" value="차가운"> 차가운 <br><hr>
		
		<input type="radio" name="beverage" value="아메리카노"> 아메리카노 <br>
		<input type="radio" name="beverage" value="티"> 티 <br><hr>
		
		<div id="teaChoice" style="display:none;">
			<input type="radio" name="teachoice" value="복숭아티"> 복숭아티 <br>
			<input type="radio" name="teachoice" value="자몽허니 블랙티"> 자몽허니 블랙티 <br>
			<input type="radio" name="teachoice" value="페퍼민트"> 페퍼민트 <br>
			<input type="radio" name="teachoice" value="캐모마일"> 캐모마일 <br>
			<input type="radio" name="teachoice" value="로즈마리"> 로즈마리 <br>
			<input type="radio" name="teachoice" value="얼그레이"> 얼그레이 <br>
			<input type="radio" name="teachoice" value="블랙퍼스트"> 블랙퍼스트 <br>
			<input type="radio" name="teachoice" value="자몽티"> 자몽티 <br>
			<input type="radio" name="teachoice" value="레몬티"> 레몬티 <br>
			<input type="radio" name="teachoice" value="유자티"> 유자티 <br><hr>
		</div>
		
		<input type="submit" value="선택하기">
	</form>
	
	
	<script type="text/javascript">

	function handleSubmit() {
		var hotorcold = document.querySelector('input[name="hotorcold"]:checked');
		var beverage = document.querySelector('input[name="beverage"]:checked');
		
		if (!hotorcold || !beverage) {
			alert("옵션을 모두 선택해 주세요.")
			return false;
		}
		
		if(beverage.value == '티') {
			document.getElementById("teaChoice").style.display = 'block';
			
			var selectedTea = document.querySelector('input[name="teachoice"]:checked')
			if(!selectedTea) {
				alert("티를 선택해 주세요.");
				return false;
			}
		}
	
		return true;
		
		}
	
	</script>
	<hr>
	<table>
		<c:forEach items="${order }" var="order">
			<tr>
				<td class="w1">${order.hotOrCold}</td>
				<td class="w2">${order.beverage}</td>
				<td class="w1">${order.teaChoice}</td>
				<td class="w1">${order.count}</td>
			</tr>
		</c:forEach>
	</table>
	


</body>
<footer><c:import url="footer.jsp"/>
</footer>
</html>