package home.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class BoardDto {
	private int no;
	private String head;
	private String writer;
	private String title;
	private String content;
	private int good;
	private int reply;
	private int read;
	private String reg;
	
	public BoardDto() {
		super();
	}
	public BoardDto(int no, String head, String writer, String title, String content, int good, int reply, int read,
			String reg) {
		super();
		this.no = no;
		this.head = head;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.good = good;
		this.reply = reply;
		this.read = read;
		this.reg = reg;
	}
	public int getNo() {
		return no;
	}
	public String getHead() {
		return head;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getGood() {
		return good;
	}
	public int getReply() {
		return reply;
	}
	public int getRead() {
		return read;
	}
	public String getReg() {
		return reg;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public void setRead(int read) {
		this.read = read;
	}
	
	public void convert(HttpServletRequest request) {
//		no = Integer.parseInt(request.getParameter("no"));
		head = request.getParameter("head");
		writer = request.getParameter("writer");
		title = request.getParameter("title");
		content = request.getParameter("content");
//		good = Integer.parseInt(request.getParameter("good"));
//		reply = Integer.parseInt(request.getParameter("reply"));
//		read = Integer.parseInt(request.getParameter("read"));
//		reg = request.getParameter("reg");
	}
	
	public void convert(ResultSet rs) throws SQLException {
		no = rs.getInt("no");
		head = rs.getString("head");
		writer = rs.getString("writer");
		title = rs.getString("title");
		content = rs.getString("content");
		good = rs.getInt("good");
		reply = rs.getInt("reply");
		read = rs.getInt("read");
		reg = rs.getString("reg");
	}
}
