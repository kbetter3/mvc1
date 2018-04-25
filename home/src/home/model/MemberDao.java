package home.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//DAO(Data Access Object)
// - DB�� ���õ� ó���� ������ ��Ƽ� ����
// - Ư���� ��찡 �ƴϸ� 1���̺� 1DAO�� ��Ģ
public class MemberDao {
//	��ü������ ����� ���� �޼ҵ�
	private Connection connect() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:xe", "sw4", "sw4");
		return con;
	}
	
//	[1] ȸ�� ���� ���
//	public void register(String id, String pw, String nick,
//			String phone, String birth, String email, String post,
//			String addr1, String addr2){
	public void register(MemberDto mdto) throws Exception{
		Connection con = this.connect();
		
		String sql = "insert into member values("
								+ "?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'), 'ȸ��', sysdate, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getId());
		ps.setString(2, mdto.getPw());
		ps.setString(3, mdto.getNick());
		ps.setString(4, mdto.getPhone());
		ps.setString(5, mdto.getBirth());
		ps.setString(6, mdto.getEmail());
		ps.setString(7, mdto.getPost());
		ps.setString(8, mdto.getAddr1());
		ps.setString(9, mdto.getAddr2());
		ps.execute();
		
		con.close();
	}
	
	//[2] �α��� ���
	public boolean login(MemberDto mdto) throws Exception{
		Connection con = this.connect();
		
		String sql = "select * from member where id=? and pw=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getId());
		ps.setString(2, mdto.getPw());
		ResultSet rs = ps.executeQuery();
		boolean result = rs.next();
		con.close();
		
		return result;
	}
	
	//[3] ��ü ��� �˻�
	public List<MemberDto> search(String type, String keyword) throws Exception{
		Connection con = connect();
		
		String sql = "select * from member "
									+ "where "+type+" like '%'||?||'%' "
									+ "order by "+type+" asc";
		System.out.println("sql = "+sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		
		List<MemberDto> list = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			MemberDto mdto = new MemberDto();
			mdto.convert(rs);
			list.add(mdto);
		}
		
		con.close();
		return list;
	}
	
	public List<MemberDto> list() throws Exception{
		Connection con = /*this.*/connect();
		
		String sql = "select * from member order by id"/*asc*/;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> list = new ArrayList<>();
		
		while(rs.next()) {
			MemberDto mdto = new MemberDto();
			mdto.convert(rs);
			list.add(mdto);
		}
		
		con.close();
		
		return list;
	}

	//[4] Ư�� ���̵� ȸ�� �˻�
	public MemberDto get(String id) throws Exception {
		Connection con = this.connect();
		
		String sql = "select * from member where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		MemberDto mdto = new MemberDto();
		if(rs.next()) {
			mdto.convert(rs);
		}
		
		con.close();
		return mdto;
	}

	//[5] Ư�� ���̵� ȸ�� Ż��
	public boolean remove(String id) throws Exception {
		Connection con = this.connect();
		
		String sql = "delete member where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		int result = ps.executeUpdate();
		
		con.close();
		
//		if(result > 0) return true;
//		else	return false;
		return result > 0;
	}

	public String findId(MemberDto mdto) throws Exception{
		Connection con = connect();
		
		String sql = "select id from member where nick=? and phone=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getNick());
		ps.setString(2, mdto.getPhone());
		ResultSet rs = ps.executeQuery();
		
		String id = rs.next()?rs.getString(1):null;
//		String id;
//		if(rs.next()){
//			id = rs.getString(1);
//		}else {
//			id = null;
//		}
		
		con.close();
		return id;
	}

	public String findPw(MemberDto mdto) throws Exception{
		Connection con = connect();
		
		String sql = "select pw from member where id=? and nick=? and phone=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getId());
		ps.setString(2, mdto.getNick());
		ps.setString(3, mdto.getPhone());
		ResultSet rs = ps.executeQuery();
		
		String pw = rs.next()?rs.getString("pw"):null;
		
		con.close();
		return pw;
	}

	//ȸ�� ���� ���� �޼ҵ�
	// - id�� �̿��Ͽ� pw, nick, phone, post, addr1, addr2, email�� ���� 
	public boolean update(MemberDto mdto) throws Exception{
		Connection con = connect();
		
		String sql = "update member "
								+ "set "
									+ "pw=?, nick=?, phone=?, email=?, "
									+ "post=?, addr1=?, addr2=? ";
		if(mdto.getPower() != null) {
			sql += ", power=? ";
		}
		sql += "where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mdto.getPw());
		ps.setString(2, mdto.getNick());
		ps.setString(3, mdto.getPhone());
		ps.setString(4, mdto.getEmail());
		ps.setString(5, mdto.getPost());
		ps.setString(6, mdto.getAddr1());
		ps.setString(7, mdto.getAddr2());
		if(mdto.getPower() == null) {
			ps.setString(8, mdto.getId());
		}
		else {
			ps.setString(8, mdto.getPower());
			ps.setString(9, mdto.getId());
		}
		int result = ps.executeUpdate();
		
		con.close();
		return result > 0;
	}
	
}







