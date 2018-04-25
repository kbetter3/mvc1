package home.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.model.MemberDao;

@WebServlet(urlPatterns="/jsp/member/login/exit.do")
public class ExitServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//[1] 준비
		String id = (String) request.getSession().getAttribute("success");
		
		//[2] 처리
		// - 탈퇴
		MemberDao mdao = new MemberDao();
		try {
			mdao.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// - 로그아웃
		//request.getSession().removeAttribute("success");
		request.getSession().invalidate();
		
		//[3] 결과
		String text = URLEncoder.encode("그동안 이용해주셔서 감사합니다", "UTF-8");
		String link = request.getContextPath()+"/jsp/index.jsp";
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}






