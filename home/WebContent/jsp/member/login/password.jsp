<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<style>
	.table th{
		padding: 0px !important;
	}
</style>

<div class="empty-row"></div>
<div class="empty-row"></div>

<div class="container-50">
	<div class="row f2">
		비밀번호 확인
	</div>
	<div class="row f3">
		d = <%=request.getParameter("d")%>
	</div>
	<div class="row">
		<form action="password.do" method="post">
			<!-- 사용자 화면에 표시되진 않지만 전송은 되는 항목 -->
			<input type="hidden" name="d" value="<%=request.getParameter("d")%>">
		
			<table class="table table-noline">
				<tbody>
					<tr>
						<th>
							<input class="form-input" type="password" name="pw" 
									required placeholder="비밀번호" maxlength="20">
						</th>
						<th>
							<input class="form-btn" type="submit" value="확인">
						</th>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

<div class="empty-row"></div>
<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>