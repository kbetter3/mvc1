package home.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		//������ �̿��Ͽ� �α��� �˻�
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		
 		String id = (String) session.getAttribute("success");
		
		//filter������ ���� ��θ� ���
		if(id == null)
			response.sendRedirect(request.getContextPath()+"/jsp/member/login.jsp");
		else
			arg2.doFilter(arg0, arg1);//����
	}

}
