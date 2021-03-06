<%@page import="home.model.MemberDto"%>
<%@page import="home.model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//서블릿을 사용해도 데이터 넘기는 법을 모르니까 여기서 바로 데이터를 가져온다
	// - 이 페이지는 로그인한 사용자만 들어올 수 있으니까
	// - 필요한 아이디는 세션에서 가져온다
	// - session.getAttribute("success")
	String id = (String)session.getAttribute("success");
	//String id = session.getAttribute("success").toString();

	//강제 예외 처리
	if(id == null)
		throw new Exception("접근 불가");
	
	MemberDao mdao = new MemberDao();
	MemberDto mdto = mdao.get(id);
%>    

<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>

<div class="container-60">
	<div class="row f2">회원 정보 수정</div>
	<div class="row">
		<form action="edit.do" method="post">
			<table class="table table-noline">
				<tbody>
					<tr>
						<th width="20%">아이디</th>
						<td>
							<!-- 아이디는 표시 후 잠금 처리 : disabled, readonly -->
							<!-- readonly는 잠겨있지만 전송은 가능 -->
							<!-- disabled는 잠겨있으면서 전송도 불가 -->
							<input class="form-input" type="text" 
								 value="<%=mdto.getId()%>" readonly name="id" required maxlength="20">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input class="form-input" type="password" name="pw" 
										required maxlength="20">
						</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>
							<input class="form-input" type="text" name="nick"
										value="<%=mdto.getNick()%>" required maxlength="8">
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input class="form-input" type="text" name="phone"
										value="<%=mdto.getPhone()%>" required maxlength="11">
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<input class="form-input" type="date" name="birth"
										value="<%=mdto.getBirthDate()%>" readonly required>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input class="form-input" type="text" 
									value="<%=mdto.getEmail()%>" name="email">
						</td>
					</tr>
					<tr>
						<th rowspan="3">주소</th>
						<td>
							<table class="table table-noline table-nopadding">
								<tbody>
									<tr>
										<th>
											<input class="form-input" type="text" name="post"
												placeholder="우편번호" value="<%=mdto.getPost()%>" >
										</th>
										<th>
											<input class="form-btn" type="button" value="검색">
										</th>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<input class="form-input" type="text" name="addr1"
											value="<%=mdto.getAddr1()%>"  placeholder="기본주소">
						</td>
					</tr>
					<tr>
						<td>
							<input class="form-input" type="text" name="addr2"
											value="<%=mdto.getAddr2()%>"  placeholder="상세주소">
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input class="form-btn" type="submit" value="정보수정">
						</th>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>