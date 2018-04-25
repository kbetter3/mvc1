package home.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.model.MemberDao;
import home.model.MemberDto;

@WebServlet(urlPatterns=/*http://localhost:8080/home*/"/jsp/member/register.do")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//[1] 파라미터
		request.setCharacterEncoding("UTF-8");
		MemberDto mdto = new MemberDto();
		mdto.convert(request);
//		mdto.setId(request.getParameter("id"));
//		mdto.setPw(request.getParameter("pw"));
//		mdto.setNick(request.getParameter("nick"));
//		mdto.setPhone(request.getParameter("phone"));
//		mdto.setBirth(request.getParameter("birth"));
//		mdto.setEmail(request.getParameter("email"));
//		mdto.setPost(request.getParameter("post"));
//		mdto.setAddr1(request.getParameter("addr1"));
//		mdto.setAddr2(request.getParameter("addr2"));
		
		//[2] DB
		MemberDao mdao = new MemberDao();
		boolean result = false;
		try{
			mdao.register(mdto);
			result = true;
		}catch(Exception e) {}
		
		//[3] 결과페이지
		String text, link;
		if(result) {
			text = URLEncoder.encode("회원 가입이 완료되었습니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/login.jsp";
		}
		else {
			text = URLEncoder.encode("회원 가입 과정에서 오류가 발생했습니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/register.jsp";
		}
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}







