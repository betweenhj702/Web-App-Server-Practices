package sm.mvc.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Board;

import static sm.mvc.model.SmSQL.*;

class SmDAO 
{
	private DataSource ds;
	
	SmDAO(){
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	ArrayList<Board> list(){
		
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		Statement stmt =null;
		ResultSet rs = null;
		String sql = LIST;
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
				String fname = rs.getString("FNAME");
				Board dto = new Board(seq, writer, email, subject, null, rdate, fname,
						null, -1);
				list.add(dto);
			}
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		return list;
	}
	ArrayList<Board> list(int page, int pageSize){
		
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = LISTP;
		int initRow = (page-1)*pageSize;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, initRow);
			pstmt.setInt(2, (initRow + pageSize));
			rs = pstmt.executeQuery();
			while(rs.next()){
				int seq = rs.getInt(2);
				String writer = rs.getString(3);
				String email = rs.getString(4);
				String subject = rs.getString(5);
				Date rdate = rs.getDate(7);
				String fname = rs.getString("fname");
				Board dto = new Board(seq, writer, email, subject, null, rdate, fname,
						null, -1);
				list.add(dto);
			}
		}catch(SQLException se){
			se.printStackTrace();
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		return list;
	}
	Board selectCon(int seq){
		
		Board dto = null;
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = CONTENT;
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
			Date rdate = rs.getDate("RDATE"); 
			String fname = rs.getString("FNAME");
			String ofname = rs.getString("OFNAME");
			long fsize = rs.getLong("FSIZE");
			dto = new  Board(seq, writer, email, subject, content, rdate, fname,
					ofname, fsize);
			return dto;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		
	}

	int delete(int seq){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = DELETE;
		int result = 0;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			result = pstmt.executeUpdate();
			return result;
		}catch(SQLException se){
			return -1;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}

	int insert(Board dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = INSERT;
		int result = 0;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFname());
			pstmt.setString(6, dto.getOfname());
			pstmt.setLong(7, dto.getFsize());
			
			result = pstmt.executeUpdate();
			return result;
		}catch(SQLException se){
			return -1;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}

	Board selUpCon(int seq){
		Board dto = null;
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = CONTENT;
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
			Date rdate = rs.getDate("RDATE"); 
			String fname = rs.getString("FNAME");
			String ofname = rs.getString("OFNAME");
			long fsize = rs.getLong("FSIZE");
			dto = new  Board(seq, writer, email, subject, content, rdate, fname,
					ofname, fsize);
			return dto;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		
	}

	int update(Board dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql = UPDATE;
		int result = 0;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFname());
			pstmt.setString(6, dto.getOfname());
			pstmt.setLong(7, dto.getFsize());
			pstmt.setInt(8, dto.getSeq());
			result = pstmt.executeUpdate();
			return result;
		}catch(SQLException se){
			return -1;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
}
