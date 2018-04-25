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

@WebServlet(urlPatterns="/jsp/member/find_pw.do")
public class FindPwServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//[1] 파라미터 - 3개(id, nick, phone)
		MemberDto mdto = new MemberDto();
		mdto.convert(request);
		
		//[2] DB
		MemberDao mdao = new MemberDao();
		String pw = null;
		try{
			pw = mdao.findPw(mdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//[3] 결과 전송
		
		String text, link;
		if(pw == null) {
			text = URLEncoder.encode("찾으시는 정보가 존재하지 않습니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/find_pw.jsp";
		}else {
			//pw 모자이크 처리
			StringBuffer buf = new StringBuffer();
			for(int i=0; i < pw.length(); i++) {
				char c = pw.charAt(i);
				if(i < 2) {
					buf.append(c);
				}else {
					buf.append("*");
				}
			}
			pw = buf.toString();
			//pw 모자이크 처리
			
			text = URLEncoder.encode("찾으시는 비밀번호는 "+pw+" 입니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/login.jsp";
		}
		
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
		
	}
}








