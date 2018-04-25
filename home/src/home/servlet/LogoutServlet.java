package home.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/jsp/member/login/logout.do")
public class LogoutServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//[1] 세션 데이터를 삭제
		HttpSession session = request.getSession();
//		session.removeAttribute("success");
		session.invalidate();
		
		//[2] index.jsp로 리다이렉트
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
	}
}





