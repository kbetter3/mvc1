package home.model;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

//DTO
// - Data Transfer Object
// - 데이터 포장용 클래스
// - DB 테이블의 1줄
public class MemberDto {
	//변수 11개
	private String id,pw,nick,phone,birth,power,reg,email,post,addr1,addr2;
	//setter & getter & 생성자
	public MemberDto() {
		super();
	}
	
	public void convert(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		this.setId(request.getParameter("id"));
		this.setPw(request.getParameter("pw"));
		this.setNick(request.getParameter("nick"));
		this.setPhone(request.getParameter("phone"));
		this.setBirth(request.getParameter("birth"));
		this.setPower(request.getParameter("power"));
		this.setReg(request.getParameter("reg"));
		this.setEmail(request.getParameter("email"));
		this.setPost(request.getParameter("post"));
		this.setAddr1(request.getParameter("addr1"));
		this.setAddr2(request.getParameter("addr2"));
	}
	
	public void convert(ResultSet rs) throws SQLException{
		this.setId(rs.getString("id"));
		this.setPw(rs.getString("pw"));
		this.setNick(rs.getString("nick"));
		this.setPhone(rs.getString("phone"));
		this.setBirth(rs.getString("birth"));
		this.setPower(rs.getString("power"));
		this.setReg(rs.getString("reg"));
		this.setEmail(rs.getString("email"));
		this.setPost(rs.getString("post"));
		this.setAddr1(rs.getString("addr1"));
		this.setAddr2(rs.getString("addr2"));
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//birth의 날짜 부분만 반환하는 getter를 추가
	public String getBirthDate() {
		return birth.substring(0, 10);
	}
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getRegDate() {
		return reg.substring(0, 10);
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	//null이 반환되지 않도록 처리
	public String getEmail() {
//		if(email == null) return "";
//		else return email;
		return email==null?"":email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPost() {
		return post==null?"":post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1==null?"":addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2==null?"":addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
}
