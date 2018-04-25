<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//쿠키 검사 코드 - save라는 이름의 쿠키에 들어있는 값을 찾아라
	Cookie[] cks = request.getCookies();
	String save = null;
	
	if(cks != null){
		for(Cookie c : cks){
			if(c.getName().equals("save")){
				save = c.getValue();
				break;
			}
		}
	}
%>    
    
<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>

<div class="container-40">
	<div class="row f2">로 그 인</div>
	<div class="row f3">save = <%=save%></div>
	<form action="login.do" method="post">
		<div class="row">
			<input class="form-input" type="text" name="id" 
							placeholder="아이디" required maxlength="20"
							value="<%=save==null?"":save%>">
		</div>
		<div class="row">
			<input class="form-input" type="password" name="pw" 
							placeholder="비밀번호" required maxlength="20">
		</div>
		<div class="row left">
			<input type="checkbox" name="save" value="remember"
					<%if(save!=null){%> checked <%}%>>	아이디 저장
		</div>
		<div class="row">
			<input class="form-btn" type="submit" value="Sign in">
		</div>
	</form>
	<div class="row left">
		<a href="find_id.jsp">아이디가 기억나지 않습니다</a>
	</div>
	<div class="row left">
		<a href="find_pw.jsp">비밀번호가 기억나지 않습니다</a>
	</div>
</div>

<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>