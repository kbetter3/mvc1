<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//application에 저장된 데이터를 꺼내와서 화면에 표 형태로 출력
	Map<String, Integer> rcMap = (Map<String, Integer>)application.getAttribute("rcMap");
	Map<String, Integer> scMap = (Map<String, Integer>)application.getAttribute("scMap");
%>
<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>

<!-- 요청 통계 -->
<div class="container-60">
	<div class="row f2">일자별 요청 통계</div>
	<div class="row">
		<table class="table table-stripe">
			<thead>
				<tr>
					<th width="50%">일자</th> 
					<th>접속량</th>
				</tr>
			</thead>
			<tbody>
				<%for(String day : rcMap.keySet()){ %>
					<tr>
						<td><%=day%></td>
						<td><%=rcMap.get(day)%></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</div>

<div class="empty-row"></div>

<!-- 세션 통계 -->
<div class="container-60">
	<div class="row f2">일자별 세션 요청 통계</div>
	<div class="row">
		<table class="table table-stripe">
			<thead>
				<tr>
					<th width="50%">일자</th> 
					<th>요청량</th>
				</tr>
			</thead>
			<tbody>
				<%for(String day : scMap.keySet()){ %>
					<tr>
						<td><%=day%></td>
						<td><%=scMap.get(day)%></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</div>

<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>








