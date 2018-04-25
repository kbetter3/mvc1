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
		//[1] �غ�
		String id = (String) request.getSession().getAttribute("success");
		
		//[2] ó��
		// - Ż��
		MemberDao mdao = new MemberDao();
		try {
			mdao.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// - �α׾ƿ�
		//request.getSession().removeAttribute("success");
		request.getSession().invalidate();
		
		//[3] ���
		String text = URLEncoder.encode("�׵��� �̿����ּż� �����մϴ�", "UTF-8");
		String link = request.getContextPath()+"/jsp/index.jsp";
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}






