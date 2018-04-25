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

@WebServlet(urlPatterns="/jsp/member/login/password.do")
public class PasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//[1] �غ� - 2���� �Ķ���� + 1���� ���ǵ�����
		String d = request.getParameter("d");
		String pw = request.getParameter("pw");
		String id = (String)request.getSession().getAttribute("success");
		MemberDto mdto = new MemberDto();
		mdto.setId(id);
		mdto.setPw(pw);
		
		//[2] ó��
		MemberDao mdao = new MemberDao();
		boolean result = false;
		try{
			result = mdao.login(mdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//[3] ���
		if(result) {
			if(d.equals("edit")) 
				response.sendRedirect(
						request.getContextPath()+"/jsp/member/login/edit.jsp");
			else if(d.equals("exit")) 
				response.sendRedirect(
						request.getContextPath()+"/jsp/member/login/exit.do");
			else if(d.equals("admin")) 
				response.sendRedirect(
						request.getContextPath()+"/jsp/admin/home.jsp");
		}
		else {
			String text = URLEncoder.encode("��� ��ȣ�� ��ġ���� �ʽ��ϴ�", "UTF-8");
			String link = request.getContextPath()+"/jsp/member/password.jsp?d="+d;
			String param = "text="+text+"&link="+link;
			response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
		}
	}
}





