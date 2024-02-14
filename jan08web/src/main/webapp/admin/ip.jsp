<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.17" rel="stylesheet" />
<link href="../css/member.css" rel="stylesheet" />
<link href="../css/adminBoard.css?ver=0.001" rel="stylesheet" />
<script type="text/javascript" src="../js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- 제이 쿼리 -->

<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          <c:forEach items="${often}" var="row">
          ['${row.ip}', ${row.count}],
          </c:forEach> 
        ]);

        var options = {
                title: '최다 방문자 TOP 10',
                legend: 'none',
                pieSliceText: 'label',
                slices: {	1: {offset: 0.1},
                			2: {offset: 0.2},
                         	3: {offset: 0.3},
                         	4: {offset: 0.4},
                          	5: {offset: 0.5},
                          	6: {offset: 0.6},
                          	7: {offset: 0.7},
                          	8: {offset: 0.8},
                          	9: {offset: 0.9},
                },
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
<script type="text/javascript">
	$(function() {
		$('#searchBtn').click(function() {
			let search = $('#search').val();
			location.href = "./ip?search=" + search;
		});
		$('.changeDel').click(function() {
			let eyes = $(this);
			let del = $(this).siblings('#del');
			let classNames = $(this).parents('tr');
			let no = $(this).parents('tr').children().first().text();
			alert(no + " : " + del);
			//여기까지 오케이

			$.ajax({
				url      : './board',
				type     : 'post',
				dataType : 'text',
				data     : {'no' : no, 'del' : del.val()},
				success  : function(result){
					if(result == 1){
						if(del.val() == 1){ // 1 -> 0
							className.attr('class','row0'); //className.css('backgroundColor', '#FFDC46');
							del.val(0);
							alert("삭제 값" + del);
							eyes.attr('class','xi-eye-off-o changeDel');
							
						} else { // 0 -> 1
							className.attr('class','row1'); //className.css('backgroundColor', '#ffffff');
							del.val(1);
							alert("삭제 값" + del);
							eyes.attr('class','xi-eye changeDel');
						}						
					} else {
						alert("문제가 발생했습니다");
					}
				},
				error    : function(error){
					alert("에러 : " + error);
				}
			});

		});
	});
</script>
</head>
<body>
	<div class="wrap">
		<!-- meuu -->
		<%@ include file="menu.jsp"%>
		<div class="main">
			<article>
				<h2>IP 관리</h2>
				1. 최다 접속 ip 5개 출력.
				2. 그래프 그리기 - 구글 차트 - ip당 접속 건수 - 10개?
				<div style="display: flex" >
				<table style="width: 20%">
					<thead>
						<tr>
							<th>ip</th>
							<th>방문횟수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${often }" var="row">
							<tr>
								<td>${row.ip }</td>
								<td>${row.count }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				 <div id="piechart" style="width: 900px; height: 500px; margin-left: 30px; background-color: #FFF0F0;"></div>
				 </div>
				
				<div class="nav-lists">
					<ul class="nav-lists-group">
						<li class="nav-lists-item" onclick="url('./board?del=1')"><i class="xi-close-circle-o"></i> 보임</li>
						<li class="nav-lists-item" onclick="url('./board?del=0')"><i class="xi-close-circle-o"></i> 숨김</li>
					</ul>
					<div style="display: flex">
						<div class="search">
							<input type="text" id="search">
							<button id="searchBtn">검색</button>
						</div>
						<button onclick="location.href='./ip'">초기화</button>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>ip</th>
							<th>방문날짜시각</th>
							<th>url</th>
							<th>정보</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
							<tr>
								<td class="d1">${row.ino }</td>
								<td class="d1"><a href="./ip?ip=${row.iip }">${row.iip }</a></td>
								<td class="d2">${row.idate }</td>
								<td class="d1">${row.iurl }</td>
								<td class="d3">${row.idata }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</article>
		</div>
	</div>
</body>
</html>
