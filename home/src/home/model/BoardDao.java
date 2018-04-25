package home.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	
	public BoardDao() {
		super();
	}
	
	private Connection connect() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:xe", "sw4", "sw4");
		return con;
	}
	
	public List<BoardDto> list() throws Exception {
		Connection conn = connect();
		String sql = "select * from board";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<>();
		
		while (rs.next()) {
			BoardDto bdto = new BoardDto();
			bdto.convert(rs);
			list.add(bdto);
		}
		
		return list;
	}

}
