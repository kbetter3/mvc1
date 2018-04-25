<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//출력을 위한 파라미터 수신 처리
	request.setCharacterEncoding("UTF-8");
	String text = request.getParameter("text");
	String link = request.getParameter("link");
%>    

<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>
<div class="empty-row"></div>

<div class="container-100">
	<div class="row f2"><%=text%></div>
	<div class="empty-row"></div>
	<div class="row f3">
		<a href="<%=link%>">이동</a>
	</div>
</div>

<div class="empty-row"></div>
<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>