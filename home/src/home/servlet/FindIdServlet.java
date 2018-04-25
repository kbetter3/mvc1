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

@WebServlet(urlPatterns="/jsp/member/find_id.do")
public class FindIdServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		//[1] 파라미터 수신
		request.setCharacterEncoding("UTF-8");
		MemberDto mdto = new MemberDto();
		mdto.setNick(request.getParameter("nick"));
		mdto.setPhone(request.getParameter("phone"));
		
		//[2] DB 처리
		MemberDao mdao = new MemberDao();
		String id = null;
		try{
			id = mdao.findId(mdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//[3] 결과 전송
		String text, link;
		if(id == null) {
			text = URLEncoder.encode("찾으시는 정보에 일치하는 ID가 없습니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/find_id.jsp";
		}else {
			text = URLEncoder.encode("찾으시는 아이디는 "+id+" 입니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/find_pw.jsp";
		}
		
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}







