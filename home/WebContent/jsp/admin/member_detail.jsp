<%@page import="home.model.MemberDto"%>
<%@page import="home.model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id");
	
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
		<a href="">VIP로 변경 또는 일반회원으로 강등</a>
	</div>
	<div class="row f3">
		<a href="member_edit.jsp?id=<%=id%>">정보 수정</a>
	</div>
	<div class="row f3">
		<a href="member_delete.do?id=<%=id%>">회원 탈퇴</a>
	</div>
</div>

<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>