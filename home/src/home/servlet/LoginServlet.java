package home.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.model.MemberDao;
import home.model.MemberDto;

@WebServlet(urlPatterns="/jsp/member/login.do")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//[1] �Ķ����
		MemberDto mdto = new MemberDto();
		mdto.setId(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		
		//[2] DB
		MemberDao mdao = new MemberDao();
		boolean result = false;
		try {
			result = mdao.login(mdto);
		}catch(Exception e) {}
		
		//[3] ���������
		String text, link;
		if(result) {
			text = URLEncoder.encode("�α��� ����", "UTF-8");
			link = request.getContextPath()+"/jsp/index.jsp";
			
			//���ǿ� �α��� ������ ���õ� �����͸� �߰�
			//�̸� : success, �� : �����ID
			HttpSession session = request.getSession();
			session.setAttribute("success", mdto.getId());

			
			try {
				//�̸� : power, �� : ����� ����
				MemberDto mdto2 = mdao.get(mdto.getId());
				session.setAttribute("power", mdto2.getPower());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//save��� �Ķ���͸� �޾Ƽ� ���� ���ο� ���� ��Ű ó���� ����
			String save = request.getParameter("save");
			if(save == null) {			//üũ ���� ��� ---> ��Ű ����(remove.jsp)
				Cookie c = new Cookie("save", mdto.getId());
				c.setPath(request.getContextPath());
				c.setMaxAge(0);
				response.addCookie(c);
			}else {							//üũ �� ��� -----> ��Ű ����(create.jsp)
				Cookie c = new Cookie("save", mdto.getId());
				c.setPath(request.getContextPath());	//������Ʈ ��ü���� ����ϵ��� ����
				c.setMaxAge(4 * 7 * 24 * 60 * 60);//4�ֵڿ� �˰ڽ��ϴ�
				response.addCookie(c);
			}
		}
		else {
			text = URLEncoder.encode("�α��� ������ ��ġ���� �ʽ��ϴ�", "UTF-8");
			link = request.getContextPath()+"/jsp/member/login.jsp";
		}
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}






