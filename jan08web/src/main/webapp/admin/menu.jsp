<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.menu1{
	/* 변수 사용 var (변수명) */
	width: var(--main);
	/* width: 150px; */
	height: 100vh;
	background-color: #C18282;
	overflow: hidden;
	position:absolute;
}
.menu1:hover{
	width: 150px;
	transition:0.5s;
}

nav{
	margin-top: 50px;
}

nav ul{
	margin: 0;
	padding: 0;
	list-style: none;
	margin: 5px;
	 	
}

nav > ul > li{
	margin: 0;
	padding: 0;
	margin: 5px 0px;
	padding: 10px 0px;
	text-align: left;
	padding-left: 9px;
	white-space: nowrap;
	overflow: hidden;
}

nav > ul > li > i{
	margin-right: 10px;
	/* firefox */
	/* -moz-margin-right: 6px;
	/* opera */
	-o-margin-right: 60px;
	/* 크롬, 엣지, 웨일 */
	-webkit-margin-right: 6px;
	/* 익스프롤러 */
	-ms-margin-right: 6px; */
	
}

nav > ul > li:hover{
	background-color: gray;
}
</style>
		<div class="menu1">
			<nav>
				<ul>
					<li onclick="url('./members')"><i class="xi-user"></i>회원 관리</li>
					<li onclick="url('./board')"><i class="xi-document"></i>게시글 관리</li>
					<li onclick="url('./comments')"><i class="xi-forum-o"></i>댓글 관리</li>
					<li onclick="url('./ip')"><i class="xi-globus"></i>ip 관리</li>
					<li onclick="url('./info')"><i class="xi-lock-o"></i>ryuuzakiumi님<li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</nav>
		</div>