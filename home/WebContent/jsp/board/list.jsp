<%@page import="home.model.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="home.model.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardDao bdao = new BoardDao();
	List<BoardDto> list = bdao.list();
%>


<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="board_list_wrap container-90">
	<table>
		<thead>
			<tr>
				<th>글번호</th><th>말머리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
			</tr>
		</thead>
		
		<tbody>
<%
			for(BoardDto bdto : list) {
%>
			<tr>
				<td><%=bdto.getNo() %></td>
				<td><%=bdto.getHead() %></td>
				<td><%=bdto.getTitle() %></td>
				<td><%=bdto.getWriter() %></td>
				<td><%=bdto.getReg() %></td>
				<td><%=bdto.getRead() %></td>
			</tr>
<%
			}
%>
		
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">12345678910 <a href="<%=request.getContextPath()%>/jsp/board/write.jsp">글쓰기</a></td>
			</tr>
		</tfoot>
	</table>
</div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>