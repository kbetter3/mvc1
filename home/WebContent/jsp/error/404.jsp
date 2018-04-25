<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="container-70">
	<div class="row f2">
		현재 찾으시는 페이지가 존재하지 않습니다
	</div>
	<div class="row">
		<img src="<%=request.getContextPath()%>/res/image/404.gif" width="100%" height="400">
	</div>
</div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>