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
		//[1] 파라미터
		MemberDto mdto = new MemberDto();
		mdto.setId(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		
		//[2] DB
		MemberDao mdao = new MemberDao();
		boolean result = false;
		try {
			result = mdao.login(mdto);
		}catch(Exception e) {}
		
		//[3] 결과페이지
		String text, link;
		if(result) {
			text = URLEncoder.encode("로그인 성공", "UTF-8");
			link = request.getContextPath()+"/jsp/index.jsp";
			
			//세션에 로그인 성공에 관련된 데이터를 추가
			//이름 : success, 값 : 사용자ID
			HttpSession session = request.getSession();
			session.setAttribute("success", mdto.getId());

			
			try {
				//이름 : power, 값 : 사용자 권한
				MemberDto mdto2 = mdao.get(mdto.getId());
				session.setAttribute("power", mdto2.getPower());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//save라는 파라미터를 받아서 존재 여부에 따라 쿠키 처리를 수행
			String save = request.getParameter("save");
			if(save == null) {			//체크 안한 경우 ---> 쿠키 삭제(remove.jsp)
				Cookie c = new Cookie("save", mdto.getId());
				c.setPath(request.getContextPath());
				c.setMaxAge(0);
				response.addCookie(c);
			}else {							//체크 한 경우 -----> 쿠키 생성(create.jsp)
				Cookie c = new Cookie("save", mdto.getId());
				c.setPath(request.getContextPath());	//프로젝트 전체에서 사용하도록 설정
				c.setMaxAge(4 * 7 * 24 * 60 * 60);//4주뒤에 뵙겠습니다
				response.addCookie(c);
			}
		}
		else {
			text = URLEncoder.encode("로그인 정보가 일치하지 않습니다", "UTF-8");
			link = request.getContextPath()+"/jsp/member/login.jsp";
		}
		String param = "text="+text+"&link="+link;
		response.sendRedirect(request.getContextPath()+"/jsp/result.jsp?"+param);
	}
}






