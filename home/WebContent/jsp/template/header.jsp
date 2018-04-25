<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>자바 SW개발자 양성반 JSP 수업</title>
<!-- 외부 CSS 라이브러리 연결 설정 -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

<!-- common.css를 불러오는 코드 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/css/common.css">
</head>
<body>
	<main>
		<header>
			<div class="container-100">
				<div class="row f1">
					JSP 2주만에 뽀개기
				</div>
			</div>
		</header>
		<nav>
			<%if(session.getAttribute("success") != null){ %>
			<a href="<%=request.getContextPath()%>/jsp/index.jsp">홈</a>
			<a href="<%=request.getContextPath()%>/jsp/member/login/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/jsp/board/list.jsp">게시판</a>
			<a href="#">게시판</a>
			<a href="<%=request.getContextPath()%>/jsp/member/login/information.jsp">내정보</a>
			<a href="<%=request.getContextPath()%>/jsp/event/home.jsp">이벤트</a>
			<%}else{ %>
			<a href="<%=request.getContextPath()%>/jsp/index.jsp">홈</a>
			<a href="<%=request.getContextPath()%>/jsp/member/register.jsp">회원가입</a>
			<a href="<%=request.getContextPath()%>/jsp/member/login.jsp">로그인</a>
			<a href="<%=request.getContextPath()%>/jsp/board/list.jsp">게시판</a>
			<a href="#">게시판</a>
			<a href="<%=request.getContextPath()%>/jsp/event/home.jsp">이벤트</a>
			<%} %>
			
			<%if(session.getAttribute("success") != null && 
						session.getAttribute("power").equals("관리자")){ %>
			<a href="<%=request.getContextPath()%>/jsp/member/login/password.jsp?d=admin">관리메뉴</a>
			<%} %>
		</nav>
		<section>
		
		
		