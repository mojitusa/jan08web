<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>톺아보기</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/detail.css" rel="stylesheet" />
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	
	$(".commentEdit").click(function(){
		if(confirm('수정하시겠습니까?')){
			//필요한 값 cno 잡기 / 수정한 내용 + 로그인 === 서블릿에서 정리		
			
			//형제 요소 cno 찾기
			let cno = $(this).siblings(".cno").val();
			alert("cno : " + cno);
			let comment = $(this).parents(".comment").children(".ccomment").text(); 
			alert(cno + " : " + comment); 
			
			
			/* let text = $(this).parent().parent().parent().children(".ccomment").text(); 
			alert("text : " + text); */
			
			
			
		}
	});
	
	//댓글 삭제 버튼을 눌렀습니다.
	$(".commentDelete").click(function(){
		alert("삭제버튼을 눌렀습니다.");
		
		//부모객체 찾아가기 = this  
		//let text = $(this).parent(".cname").text(); //val()? text()? html()?
				
		//부모요소 아래 자식요소 찾기 children()
		//let cno = $(this).parent(".cname").chidren(".cno").val();
		//let cno = $(this).parent().children(".cno").val();
		
		//형제요소 찾기 .siblings()   .prev() 바로 이전  .next() 바로다음
		//let cno = $(this).siblings(".cno").val();
		
		if(confirm("삭제 하시겠습니까?")){
			
			let cno = $(this).prev().val();
		
			//ajax
			let point = $(this).closest(".comment");
			$.ajax({
				url : './commentDel',	// 주소
				type : 'post',			// get, post
				dataType : 'text',		// 수신 타입 json
				data : {no : cno},			// 보낼 값
				success:function(result){  //0,1
					alert("서버에서 온 값 : " + result);
					if(result == 1){
						//정상 삭제 : this의 부모(.comment)를 찾아서 remove 하겠습니다.
						//alert(parent);
						//$(this).closest(".comment").hide();
						point.remove();
					} else {
						alert("삭제할 수 없습니다. 관리자에게 문의하세요.")
					}
				},
				error:function(request, status, error){
					alert("문제가 발생했습니다.");
				}
			}); // end ajax
		}
		
	});
	
	//댓글쓰기 버튼을 누르면 댓글창 나오게 하기 2024-01-24
	$(".comment-write").hide(); //원래는 ready 바로 아래 두는 것을 추천
	//$("comment-write").show(5000);
	
	$(".xi-comment-o").click(function(){
		$(".xi-comment-o").hide();
		//$(".comment-write").show();
		$(".comment-write").slideToggle('slow');
	});
	
	
	
	
	+
	//alert("준비되었습니다");
	$("#comment-btn").click(function(){
		let content = $("#commentcontent").val();
		let bno = ${detail.no }; //여기는 글번호
		//alert("content : " + content + " no : " + no);
		//가상 form 만들기 = 동적생성
		//전송 ---> content가 5글자 이상인 경우 실행하겠습니다.
		if(content.length < 5){
			alert("댓글은 다섯글자 이상으로 적어주세요.");
			$("#commentcontent").focus();
			//return false;
		} else {
			let form = $('<form></form>');
			form.attr('name', 'form');
			form.attr('method', 'post');
			form.attr('action', './comment');
			
			form.append($('<input/>', {type:'hidden', name:'commentcontent', value:content}));//json
			form.append($('<input/>', {type:'hidden', name:'bno', value:bno}));
			
			form.appendTo("body");
			form.submit();
			
			/* let form = document.createElement('form');
			form.name='form';
			form.method='post';
			form.action='./comment';
			//붙이기
			let text = document.createElement('input');
			text.setAttribute("type", "hidden");
			text.setAttribute("name", "commentcontent");
			text.setAttribute("value", content);
			//붙이기
			let no = document.createElement('input');
			no.setAttribute("type", "hidden");
			no.setAttribute("name", "bno");
			no.setAttribute("value", ${detail.no});
			//form에다가 붙이기
			form.appendChild(text);
			form.appendChild(no);
			//전송하기
			document.body.appendChild(form);
			form.submit(); */
		}
	});
});


</script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<div class="detailDIV">
						<div class="detailTITLE">${detail.title }</div>
						<div class="detailWRITECOUNT">
							<div class="detailWRITE">
								${detail.write }

								<c:if
									test="${sessionScope.mname ne null && detail.mid eq sessionScope.mid }">
									<img alt="삭제" src="./img/delete.png" onclick="del()">
									<img alt="수정" src="./img/edit.png" onclick="update()">
								</c:if>

							</div>
							<div class="detailCOUNT">${detail.ip}/${detail.count }</div>
						</div>
						<div class="detailCONTENT">${detail.content }</div>
					</div>
					<c:if test="${sessionScope.mid ne null }">
						<button class="xi-comment-o">댓글쓰기</button>
						<!-- 댓글쓰는 창을 여기다가 만들어주겠습니다. 댓글내용, 누가, 어느, 2024-01-22 -->
						<div class="comment-write">
							<div class="comment-form">
								<textarea id="commentcontent"></textarea>
								<button id="comment-btn">댓글쓰기</button>
							</div>
						</div>
					</c:if>
					<!-- 댓글 출력창 -->
					<div class="comments">
						<c:forEach items="${commentList }" var="co">
							<div class="comment">
								<div class="chead">
									<div class="cname">${co.mname }님
										<c:if
											test="${sessionScope.mname ne null && co.mid eq sessionScope.mid }">
											<input type="hidden" class="cno" value="${co.cno}">
											<img alt="삭제" src="./img/delete.png" class="commentDelete">
											<img alt="수정" src="./img/edit.png" class="commentEdit">
										</c:if>
									</div>
									<div class="cdate">${co.cip}/${co.cdate }</div>
								</div>
								<div class="ccomment">${co.comment }</div>
							</div>
						</c:forEach>
					</div>
					<button onclick="url('./board?page=${param.page}')">게시판으로</button>
				</article>
				<article>하단 foot때문에 안 보인다면 추가</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp" />
		</footer>
	</div>

	<script type="text/javascript">
		function del(){
			//alert("삭제를 눌렀습니다");
			var ch = confirm("글을 삭제하시겠습니까?"); 
			if(ch){				
				location.href="./delete?no=${detail.no }";
			}
		}
		function update(){
			if(confirm("수정하시겠습니까?")){
				location.href="./update?no=${detail.no }";
			}
		}
		/* function commentDel(cno){
			if (confirm("댓글을 삭제하시겠습니까?")) {
				location.href='./commentDel?no=${detail.no}&cno='+cno;
			}
		} */
	</script>
</body>
</html>