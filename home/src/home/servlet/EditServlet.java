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

@WebServlet(urlPatterns= {
		"/jsp/member/login/edit.do",
		"/jsp/admin/member_edit.do"
})
public class EditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//[1] 준비(파라미터, 세션...)
		MemberDto mdto = new MemberDto();
		mdto.convert(request);
//		mdto.setId(request.getParameter("id"));
//		mdto.setPw(request.getParameter("pw"));
//		mdto.setAddr1(request.getParameter("addr1"));
//		mdto.setAddr2(request.getParameter("addr2"));
//		mdto.setPost(request.getParameter("post"));
//		mdto.setEmail(request.getParameter("email"));
//		mdto.setPhone(request.getParameter("phone"));
		
		//[2] 처리(Dao)
		MemberDao mdao = new MemberDao();
		boolean result = false;
		try{
			result = mdao.update(mdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//[3] 결과(리다이렉트)
		if(result) {
			if(mdto.getPower() == null) {
//				본인 정보 수정일 때
				response.sendRedirect("information.jsp");
//				response.sendRedirect(request.getContextPath()+"/jsp/member/information.jsp");
			}else {
//				관리자 수정일 때
				response.sendRedirect("member_detail.jsp?id="+mdto.getId());
			}
		}
		else {
			String text = URLEncoder.encode("사용중인 닉네임입니다", "UTF-8");
			String link;
			if(mdto.getPower() == null)
				link = request.getContextPath()+"/jsp/member/login/edit.jsp";
			else
				link = request.getContextPath()+"/jsp/admin/member_edit.jsp?id="+mdto.getId();
			String param = "text="+text+"&link="+link;
			response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
		}
	}
}











