<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="container-70">
	<div class="row f2">
		처리 과정에서 일시적인 오류가 발생하였습니다
	</div>
	<div class="row">
		<img src="<%=request.getContextPath()%>/res/image/500.jpg" width="100%" height="400">
	</div>
</div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>
