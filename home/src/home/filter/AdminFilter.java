package home.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//DB에 접속을 할것인지 말것인지 결정
		// - session에 power값을 저장한 뒤 사용
		// - Login 성공시 세션에 power값도 저장하여 사용(power 변경시 세션도 갱신)
		
			HttpServletRequest request = (HttpServletRequest) arg0;
			HttpServletResponse response = (HttpServletResponse) arg1;
			HttpSession session = request.getSession();
			
			//로그인 필터 검사 후이므로 power는 절대로 null일 수 없다
	 		String power = (String) session.getAttribute("power");
			
			//filter에서는 절대 경로를 사용
			if(power.equals("관리자"))
				arg2.doFilter(arg0, arg1);//속행
			else {
				//사용자에게 오류 전송
				response.sendError(500, "관리자 기능 접근 권한 없음");
			}
	}
	
}







