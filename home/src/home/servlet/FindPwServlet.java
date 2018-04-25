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
		//[1] �Ķ���� - 3��(id, nick, phone)
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
		
		//[3] ��� ����
		
		String text, link;
		if(pw == null) {
			text = URLEncoder.encode("ã���ô� ������ �������� �ʽ��ϴ�", "UTF-8");
			link = request.getContextPath()+"/jsp/member/find_pw.jsp";
		}else {
			//pw ������ũ ó��
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
			//pw ������ũ ó��
			
			text = URLEncoder.encode("ã���ô� ��й�ȣ�� "+pw+" �Դϴ�", "UTF-8");
			link = request.getContextPath()+"/jsp/member/login.jsp";
		}
		
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
		
	}
}








