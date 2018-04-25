package home.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns="/*")
@WebFilter(urlPatterns= {"*.jsp", "*.do"})
public class CountFilter implements Filter{
//	private int sessionCount = 0;
//	private int requestCount = 0;
	
	//���� �����ϱ� ���� �����
	private Map<String, Integer> scMap = new TreeMap<>(Collections.reverseOrder());
	private Map<String, Integer> rcMap = new TreeMap<>(Collections.reverseOrder());
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession();
		if(session.isNew()) {
			//���� ���� �ڵ�
			increaseSessionCount();
		}
		//��û Ƚ�� ���� �ڵ�
		increaseRequestCount();
		
		//���
		arg2.doFilter(arg0, arg1);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//���Ͱ� �ʱ�ȭ�� �� �ڵ� ȣ��Ǵ� �޼ҵ�
		// - �������� �ʱ�ȭ, �ҷ����� ���� ����
		System.out.println("CountFilter �ʱ�ȭ");
		
		this.dir = filterConfig.getServletContext().getRealPath("");
		loadMap(dir);
		
		//��� ������� ��û���� ������ �� �ֵ��� application�� ����� ����
		filterConfig.getServletContext().setAttribute("scMap", scMap);
		filterConfig.getServletContext().setAttribute("rcMap", rcMap);
	}
	
	String dir;
	
	@Override
	public void destroy() {
		//������ ������ �� �� �� �ڵ� ȣ��Ǵ� �޼ҵ�
		// - �������� ���� ���� ����
		System.out.println("CountFilter ����");
		saveMap(dir);
	}
	
	//���� ���� �޼ҵ�
	private void increaseSessionCount() {
		String today = getToday();
		Integer sessionCount = scMap.get(today);
		if(sessionCount == null) sessionCount = 0;
		sessionCount++;
		scMap.put(today, sessionCount);
		System.out.println("["+today+"]�ű� ���� �߻� : "+sessionCount);
	}
	//��û ���� �޼ҵ�
	private void increaseRequestCount() {
		String today = getToday();
		Integer requestCount = rcMap.get(today);
		if(requestCount == null) requestCount = 0;
		requestCount++;
		rcMap.put(today, requestCount);
		System.out.println("["+today+"]�ű� ��û �߻� : "+requestCount);
	}
	//���� ��¥ ��ȯ �޼ҵ� : yyyy-MM-dd
	private String getToday() {
		Date d = new Date();
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(d);
	}
	
	//���� ���� �� ����
	private void saveMap(String dir) {
		File target = new File(dir, "count.db");
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(target));){
			out.writeObject(rcMap);
			out.writeObject(scMap);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadMap(String dir) {
		File target = new File(dir, "count.db");
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(target));){
			rcMap = (Map<String, Integer>) in.readObject();
			scMap = (Map<String, Integer>) in.readObject();
		}catch(Exception e) {
			rcMap = new TreeMap<>(Collections.reverseOrder());
			scMap = new TreeMap<>(Collections.reverseOrder());
		}
	}

}









