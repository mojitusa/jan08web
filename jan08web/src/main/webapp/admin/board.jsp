<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.14" rel="stylesheet"/>
<link href="../css/member.css" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- 제이 쿼리 -->

<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript">
$(function(){
	$('#saerchBtn' ).click(function (){
		let search = $('search').val();
		location.href="./board?search="+search;
	});
	$('.changeDel').click(function(){
		let eyes = $(this)
		let del = $(this).siblings('#del').val();
		let no = $(this).parents('tr').children().first().text();
		let classNames = $(this).parents('tr');
	
		$.ajax({
			url		: './board',
			type	: 'post',
			dataType: 'text',
			data	: {'no' : no, 'del' : del},
			success	: function(result){
				alert("결과 : " + result);
				
				if(del ==1) {
					className.attr('class', 'row0'); //클래스명을 바꿔 주는 건가?
					//className.css('backgroundColor', 'gray');
					del.val(0);
					eyes.attr('class', 'xi-eye-off-o changeDel');
				} else { // 0 -> 1
					className.attr('class', 'row1');
					//className.css('backgroundColor', 'white');
					del.val(1);
					eyes.attr('class', 'xi-eye changeDel')
				}
			} else {
				
				
			}
				
			},
			error 	:function(error){
				alert("에러 :" + result);
			}
		});
		
		
		
		
		
		
	/* 	alert("del 값은?" + del);
		if (del == 1) {
			del = 0;
		alert("숨김으로 변경합니다.")
			
		} else {
			del = 1
		alert("숨김을 해체합니다.")
		}
		
		
		let form = $('<form></form>');
		form.attr('method', 'post');
		form.attr('action', './board');
		form.append($('<input/>', {type : 'hidden', name : 'no', value : no}));
		form.append($('<input/>', {type : 'hidden', name : 'del', value : del}));
		form.appendTo('body');
		form.submit(); */
		
	});
});
</script>
</head>
<body>
	<div class="wrap">
		<!-- meuu -->
		<%@ include file="menu.jsp" %>
		<article>
			<h2>게시글 관리</h2>
			<div class="search">
				<input type="text" id="search"><button id="searchBtn">검색</button>
			</div>
			<table>
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>글쓴이</th>
						<th>쓴날짜</th>
						<th>IP</th>
						<th>조회수</th>
						<th>댓글수</th>
						<th>삭제여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="row">
						<tr>
							<td>${row.no }</td>
							<td>${row.title }</td>
							<td>${row.write }</td>
							<td>${row.date }</td>
							<td>${row.ip }</td>
							<td class="di">${row.count }</td>
							<td>${row.comment }</td>
							<td class="di">
							<input type="hidden" id="del" value="${row.del }">
							<c:if test="${row.del eq 1 }"><i class="xi-eye changeDel"></i></c:if>
							<c:if test="${row.del eq 0 }"><i class="xi-eye-off-o changeDel"></i></c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</article>
	</div>
</body>
</html>
