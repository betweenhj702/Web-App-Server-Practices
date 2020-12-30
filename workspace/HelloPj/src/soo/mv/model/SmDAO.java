package soo.mv.model;

import java.sql.*;

public class SmDAO 
{
	private DataSource ds;
	
	public SmDAO(){
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	public ArrayList<SmDTO> list(){
		ArrayList<SmDTO> list = new ArrayList<SmDTO>();
		Connection con = null;
		Statement stmt =null;
		ResultSet rs = null;
		String sql = "select * from BOARD order by SEQ desc";
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				Date rdate = rs.getDate(6);
				
				SmDTO dto = new SmDTO(seq, writer, email, subject, rdate);
				list.add(dto);
			}
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		return list;
	}

	public SmDTO selectCon(int seq){
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from BOARD where SEQ=?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			rs.next();
			String writer = rs.getString(2);
			String email = rs.getString(3);
			String subject = rs.getString(4);
			String content = rs.getString(5);

			SmDTO dto = new SmDTO(seq, writer, email, subject, content);
			
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		return dto;
	}

	public void delete(int seq){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "delete from BOARD where SEQ=?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}

	public void insert(SmDTO dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.writer);
			pstmt.setString(2, dto.email);
			pstmt.setString(3, dto.subject);
			pstmt.setString(4, dto.content);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}

	public SmDTO selUpCon(int seq){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "select * from BOARD where SEQ = ?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt1.executeQuery();
			rs.next();
			String writer = rs.getString(2);
			String email = rs.getString(3);
			String subject = rs.getString(4);
			String content = rs.getString(5);
			SmDTO dto = new SmDTO(seq, writer, email, subject, content);
		
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
		return dto;
	}

	public void update(int seq, SmDTO dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = "update BOARD set WRITER=?, EMAIL=?, SUBJECT=?, CONTENT=? where SEQ=?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.writer);
			pstmt.setString(2, dto.email);
			pstmt.setString(3, dto.subject);
			pstmt.setString(4, dto.content);
			pstmt.setInt(5, seq);
			pstmt.executeUpdate();
			
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
}
