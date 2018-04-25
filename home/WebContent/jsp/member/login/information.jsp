<%@page import="home.model.MemberDto"%>
<%@page import="home.model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//서블릿을 사용해도 데이터 넘기는 법을 모르니까 여기서 바로 데이터를 가져온다
	// - 이 페이지는 로그인한 사용자만 들어올 수 있으니까
	// - 필요한 아이디는 세션에서 가져온다
	// - session.getAttribute("success")
	String id = (String)session.getAttribute("success");
	//String id = session.getAttribute("success").toString();

	//강제 예외 처리 - LoginFilter에서 처리
	//if(id == null)
	//	throw new Exception("접근 불가");
	
	MemberDao mdao = new MemberDao();
	MemberDto mdto = mdao.get(id);
%>    


<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>

<div class="container-70">
	<div class="row f2">회원 정보 보기</div>
	<div class="row">
		<table class="table">
			<tbody>
				<tr>
					<th width="20%">아이디</th>
					<td><%=mdto.getId()%></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><%=mdto.getNick()%></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><%=mdto.getPhone()%></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><%=mdto.getBirth()%></td>
				</tr>
				<tr>
					<th>등급</th>
					<td><%=mdto.getPower()%></td>
				</tr>
				<tr>
					<th>가입일</th>
					<td><%=mdto.getReg()%></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><%=mdto.getEmail()%></td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						[<%=mdto.getPost()%>] 
						<%=mdto.getAddr1()%> 
						<%=mdto.getAddr2()%>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row f3">
		<a href="password.jsp?d=edit">정보 수정</a>
	</div>
	<div class="row f3">
		<a href="password.jsp?d=exit">회원 탈퇴</a>
	</div>
</div>

<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>