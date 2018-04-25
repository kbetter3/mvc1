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
	
	//누적 관리하기 위한 저장소
	private Map<String, Integer> scMap = new TreeMap<>(Collections.reverseOrder());
	private Map<String, Integer> rcMap = new TreeMap<>(Collections.reverseOrder());
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession();
		if(session.isNew()) {
			//세션 증가 코드
			increaseSessionCount();
		}
		//요청 횟수 증가 코드
		increaseRequestCount();
		
		//통과
		arg2.doFilter(arg0, arg1);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//필터가 초기화될 때 자동 호출되는 메소드
		// - 데이터의 초기화, 불러오기 등을 수행
		System.out.println("CountFilter 초기화");
		
		this.dir = filterConfig.getServletContext().getRealPath("");
		loadMap(dir);
		
		//모든 사용자의 요청에서 접근할 수 있도록 application에 저장소 설정
		filterConfig.getServletContext().setAttribute("scMap", scMap);
		filterConfig.getServletContext().setAttribute("rcMap", rcMap);
	}
	
	String dir;
	
	@Override
	public void destroy() {
		//필터의 수명이 다 할 때 자동 호출되는 메소드
		// - 데이터의 저장 등을 수행
		System.out.println("CountFilter 종료");
		saveMap(dir);
	}
	
	//세션 증가 메소드
	private void increaseSessionCount() {
		String today = getToday();
		Integer sessionCount = scMap.get(today);
		if(sessionCount == null) sessionCount = 0;
		sessionCount++;
		scMap.put(today, sessionCount);
		System.out.println("["+today+"]신규 세션 발생 : "+sessionCount);
	}
	//요청 증가 메소드
	private void increaseRequestCount() {
		String today = getToday();
		Integer requestCount = rcMap.get(today);
		if(requestCount == null) requestCount = 0;
		requestCount++;
		rcMap.put(today, requestCount);
		System.out.println("["+today+"]신규 요청 발생 : "+requestCount);
	}
	//오늘 날짜 반환 메소드 : yyyy-MM-dd
	private String getToday() {
		Date d = new Date();
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(d);
	}
	
	//파일 저장 및 열기
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









