package home.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns="/*")
public class KoreanFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//System.out.println("한글 처리 완료");
		
		//한글처리
		HttpServletRequest request = (HttpServletRequest) arg0;
		request.setCharacterEncoding("UTF-8");
		
		//통과
		arg2.doFilter(arg0, arg1);
	}

}
