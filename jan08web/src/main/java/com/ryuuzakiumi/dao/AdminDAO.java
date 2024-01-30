package com.ryuuzakiumi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryuuzakiumi.dto.BoardDTO;
import com.ryuuzakiumi.dto.MemberDTO;

public class AdminDAO extends AbstractDAO {
	
	public List<MemberDTO> memberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt("mno"));
				e.setMid(rs.getString("mid"));
				e.setMname(rs.getString("mname"));
				e.setMdate(rs.getString("mdate"));
				e.setMgrade(rs.getInt("mgrade"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}


	public List<MemberDTO> memberList(int grade) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member WHERE mgrade=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grade);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt("mno"));
				e.setMid(rs.getString("mid"));
				e.setMname(rs.getString("mname"));
				e.setMdate(rs.getString("mdate"));
				e.setMgrade(rs.getInt("mgrade"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public int membergradeUpdate(MemberDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mgrade=? WHERE mno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMgrade());
			pstmt.setInt(2, dto.getMno());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		if (result == 1) {
			System.out.println("등급 업데이트 성공");
		} else if (result == 0) {
			System.out.println("등급 업데이트 실패");
		}
		
		
		return result;
	}


	public int findUserMgrade(Object attribute) {
		int result = 0;
		
		String mid = (String)attribute; 
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT mgrade FROM member WHERE mid=?";
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt("mgrade");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public List<BoardDTO> boardList() {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =
//				"SELECT board_no, board_title, board_date, board_ip,"
//				+ "(SELECT COUNT(*) FROM visitcount v WHERE b.board_no = v.board_no) AS count"
//				+ "(SELECT mname FROM member WHERE member.mno = b.mno ) AS mname,"
//				+ "(SELECT COUNT(*) form comment where comment.board_no = board.board_no) AS comment,"
//				+ "board_del FROM board b ";
				//이게 내가 처음 생각했던 쿼리문 근데 틀렸다.
	/*	String sql = "SELECT board_no, board_title, board_date, board_ip, board_del, "
				+ "(SELECT COUNT(*) FROM visitcount v WHERE b.board_no = v.board_no) AS count"
				+ "(SELECT COUNT(*) form comment c where c.board_no = board.board_no) AS comment, "
				+ "m.mname FROM board b JOIN member m ON b.mno = m.mno"
				+ "ORDER BY board_no DESC"; */
				
				"SELECT board_no, board_title, board_date, board_ip, board_del, " 
				+ "(SELECT count(*) FROM visitcount v WHERE v.board_no=b.board_no) as COUNT, "
				+ "(SELECT count(*) FROM comment c WHERE c.board_no=b.board_no) as comment, "
				+ "m.mname "
				+ "FROM board b "
				+ "JOIN member m ON b.mno=m.mno "
				+ "ORDER BY board_no DESC";
		
		try {
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname")); //member에서 옵니다.
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("count")); //visitcount에서 옵니다.
				e.setComment(rs.getInt("comment")); //댓글에서 옵니다.
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));
				
				list.add(e);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con); 
		}
		
		
		return list;
	}


	public List<BoardDTO> boardList(String parameter) {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =
				"SELECT board_no, board_title, board_date, board_ip, board_del, " 
				+ "(SELECT count(*) FROM visitcount v WHERE v.board_no=b.board_no) as COUNT, "
				+ "(SELECT count(*) FROM comment c WHERE c.board_no=b.board_no) as comment, "
				+ "m.mname "
				+ "FROM board b "
				+ "JOIN member m ON b.mno=m.mno "
				+ "WHERE board_title LIKE CONCAT ('%', '?', '%')\r\n"
				+ "OR board_content LIKE CONCAT ('%', '?', '%')\r\n"
				+ "OR mname LIKE CONCAT ('%', '?', '%')"
				+ "ORDER BY board_no DESC";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, parameter);
			pstmt.setString(2, parameter);
			pstmt.setString(3, parameter);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO e = new BoardDTO();
				
				while (rs.next()) {
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname")); //member에서 옵니다.
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("count")); //visitcount에서 옵니다.
				e.setComment(rs.getInt("comment")); //댓글에서 옵니다.
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));
				
				list.add(e);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con); 
		}
		
		
		return list;
	}


	public int boardDel(BoardDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del=? WHERE board_no=?";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getDel() +"");
			pstmt.setInt(2, dto.getNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		
		
		return result;
	}
		

}
