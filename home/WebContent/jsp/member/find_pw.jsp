<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/template/header.jsp"/>

<div class="empty-row"></div>
<div class="empty-row"></div>

<form action="find_pw.do" method="post">
	<div class="container-50">
		<div class="row f2">
			비밀번호 찾기
		</div>
		<div class="row">
			<input class="form-input" type="text" name="id" placeholder="아이디 입력" required>
		</div>
		<div class="row">
			<input class="form-input" type="text" name="nick" placeholder="닉네임 입력" required>
		</div>
		<div class="row">
			<input class="form-input" type="text" name="phone" placeholder="전화번호 입력" required>
		</div>
		<div class="row">
			<input class="form-btn" type="submit" value="찾기">
		</div>
	</div>
</form>

<div class="empty-row"></div>
<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"/>