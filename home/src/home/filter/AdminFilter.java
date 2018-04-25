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

public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//DB�� ������ �Ұ����� �������� ����
		// - session�� power���� ������ �� ���
		// - Login ������ ���ǿ� power���� �����Ͽ� ���(power ����� ���ǵ� ����)
		
			HttpServletRequest request = (HttpServletRequest) arg0;
			HttpServletResponse response = (HttpServletResponse) arg1;
			HttpSession session = request.getSession();
			
			//�α��� ���� �˻� ���̹Ƿ� power�� ����� null�� �� ����
	 		String power = (String) session.getAttribute("power");
			
			//filter������ ���� ��θ� ���
			if(power.equals("������"))
				arg2.doFilter(arg0, arg1);//����
			else {
				//����ڿ��� ���� ����
				response.sendError(500, "������ ��� ���� ���� ����");
			}
	}
	
}







