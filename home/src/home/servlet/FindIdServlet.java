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
		
		//[1] �Ķ���� ����
		request.setCharacterEncoding("UTF-8");
		MemberDto mdto = new MemberDto();
		mdto.setNick(request.getParameter("nick"));
		mdto.setPhone(request.getParameter("phone"));
		
		//[2] DB ó��
		MemberDao mdao = new MemberDao();
		String id = null;
		try{
			id = mdao.findId(mdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//[3] ��� ����
		String text, link;
		if(id == null) {
			text = URLEncoder.encode("ã���ô� ������ ��ġ�ϴ� ID�� �����ϴ�", "UTF-8");
			link = request.getContextPath()+"/jsp/member/find_id.jsp";
		}else {
			text = URLEncoder.encode("ã���ô� ���̵�� "+id+" �Դϴ�", "UTF-8");
			link = request.getContextPath()+"/jsp/member/find_pw.jsp";
		}
		
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}







