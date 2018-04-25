<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>

<div class="container-60">
	<div class="row f2">가입 정보 입력</div>
	<div class="row">
		<form action="register.do" method="post">
			<table class="table table-noline">
				<tbody>
					<tr>
						<th width="20%" rowspan="2">아이디</th>
						<td>
							<input class="form-input" type="text" name="id" required maxlength="20">
						</td>
					</tr>
					<tr>
						<td>
							<a href="#">아이디 중복확인</a>
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
										required maxlength="8">
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input class="form-input" type="text" name="phone"
										required maxlength="11">
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<input class="form-input" type="date" name="birth"
										required>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input class="form-input" type="text" name="email">
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
												placeholder="우편번호">
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
											placeholder="기본주소">
						</td>
					</tr>
					<tr>
						<td>
							<input class="form-input" type="text" name="addr2"
											placeholder="상세주소">
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input class="form-btn" type="submit" value="Sign up">
						</th>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

<div class="empty-row"></div>

<jsp:include page="/jsp/template/footer.jsp"></jsp:include>