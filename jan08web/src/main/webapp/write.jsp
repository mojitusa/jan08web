<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style style="text.css">
#title{
	width: 100%;
	height: 30px;
	margin-bottom: 10px;
	}
</style>
</head>
<body>
	<div class="container1">
		<header>
			<%@ include file="menu.jsp" %>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>글쓰기</h1>
					<div>
						<form action="./write" method="post">
							<input type="text" name="title">
							<textarea id="summernote" name="content"></textarea>
							<button type="submit">저장하기</button>
						</form>
					</div>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
	<script>
    $(document).ready(function() {
        $('#summernote').summernote();
    });
  </script>
</body>
</html>