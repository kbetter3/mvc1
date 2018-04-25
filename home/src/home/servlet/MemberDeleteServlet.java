package home.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.model.MemberDao;

@WebServlet(urlPatterns="/jsp/admin/member_delete.do")
public class MemberDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
//		[1] 준비
		String id = request.getParameter("id");
		
//		[2] 처리
		MemberDao mdao = new MemberDao();
		try {
			mdao.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		[3] 결과
		response.sendRedirect("list.jsp");
//		response.sendRedirect(request.getContextPath()+"/jsp/admin/list.jsp");
	}
}









