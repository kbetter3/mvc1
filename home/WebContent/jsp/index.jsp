<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="container-60">
	<div class="row f2">
		홈페이지 방문을 환영합니다!
	</div>
	<div class="row">
		<img src="<%=request.getContextPath()%>/res/image/welcome.jpg" width="100%" height="400">
	</div>
</div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>




