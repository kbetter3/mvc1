<%@page import="home.model.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="home.model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
회원의 목록을 가져와서 id, nick, phone, birth, power, reg, email 을 테이블로 출력 
-->
<%
	//파라미터가 있는지 여부에 따라 목록과 검색으로 구분하여 처리
	
	//[1] 파라미터 수신
	String type = request.getParameter("type");
	String keyword = request.getParameter("keyword");
	
	//[2] DB 처리
	boolean searchMode = type != null && keyword != null;

	MemberDao mdao = new MemberDao();
	List<MemberDto> list;
	if(searchMode){
		list = mdao.search(type, keyword);
	}else{
		list = mdao.list();
	}
%>

<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="container-90">
	<div class="row f2">회원 목록</div>
	<div class="row">
		<div class="container-60">
			<div class="row">
				<form action="list.jsp" method="get">
					<table class="table table-noline table-nopadding">
						<tbody>
							<tr>
								<th>
									<select class="form-input" name="type">
										<option value="id">아이디</option>
										<option value="nick">닉네임</option>
										<option value="phone">전화번호</option>
										<option value="power">등급</option>
									</select>
								<th width="60%">
									<input class="form-input" type="text" name="keyword" placeholder="검색어" required> 
								</th>
								<th>
									<input class="form-btn" type="submit" value="검색">
								</th>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<table class="table table-stripe">
			<thead>
				<tr>
					<th>아이디</th>
					<th>닉네임</th>
					<th>전화번호</th>
					<th>생년월일</th>
					<th>회원등급</th>
					<th>가입일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody align="center">
				<%for(MemberDto mdto : list){ %>
				<tr>
					<td><%=mdto.getId()%></td>
					<td><%=mdto.getNick()%></td>
					<td><%=mdto.getPhone()%></td>
					<td><%=mdto.getBirthDate()%></td>
					<td><%=mdto.getPower() %></td>
					<td><%=mdto.getRegDate() %></td>
					<td>
						<a href="member_detail.jsp?id=<%=mdto.getId()%>">
							<i class="fa fa-eye"></i>
						</a> 
						&nbsp;&nbsp;
						<a href="member_edit.jsp?id=<%=mdto.getId()%>">
							<i class="fa fa-edit"></i>
						</a>
						&nbsp;&nbsp;
						<a href="member_delete.do?id=<%=mdto.getId()%>">
							<i class="fa fa-trash"></i>
						</a>
					</td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>