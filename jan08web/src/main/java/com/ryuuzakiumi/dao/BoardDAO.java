package com.ryuuzakiumi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryuuzakiumi.db.DBConnection;
import com.ryuuzakiumi.dto.BoardDTO;
import com.ryuuzakiumi.dto.CommentDTO;
import com.ryuuzakiumi.util.Util;

public class BoardDAO extends AbstractDAO {

	public List<BoardDTO> boardList(int page) {
		List<BoardDTO> list = null;
		// db정보
		DBConnection db = DBConnection.getInstance();
		// conn 객체
		Connection con = null;
		// pstmt
		PreparedStatement pstmt = null;
		// rs
		ResultSet rs = null;
		// sql
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		con = db.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10);
			// 1개의 버튼에 10개의 글이 나와야 한다.
			// 그리고 시작은 0으로 시작해야 한다.

			rs = pstmt.executeQuery();
			list = new ArrayList<BoardDTO>();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("board_write"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setComment(rs.getInt("comment"));
				e.setIp(Util.maskIp(rs.getString("board_ip")));
				// 숫자든 이름이든 하나로 통일하는 게 좋다

				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}

		return list;
	}

	public BoardDTO detail(int no) {
		//countUp(no);

		BoardDTO dto = new BoardDTO();
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no, b.board_title, b.board_content, m.mname as board_write, m.mid, "
				+ "b.board_date, b.board_ip," + "(SELECT COUNT(*) FROM visitcount WHERE board_no = b.board_no) AS board_count "
				+ "FROM board b JOIN member m ON b.mno=m.mno " + "WHERE b.board_no=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setContent(rs.getString("board_content"));
				dto.setWrite(rs.getString("board_write"));
				dto.setDate(rs.getString("board_date"));
				dto.setCount(rs.getInt("board_count"));
				dto.setMid(rs.getString("mid"));
				dto.setIp(Util.maskIp(rs.getString("board_ip")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dto;
	}

	public void countUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		String sql = "SELECT count(*) FROM visitcount "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int result = rs.getInt(1);
				if (result == 0) {
					realCountUp(no, mid);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	private void realCountUp(int no, String mid) {
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO visitcount (board_no, mno) VALUES (?, (SELECT mno FROM member WHERE mid=?))";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			pstmt.execute();
			System.out.println("쿼리를 실행합니다");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	public int write(BoardDTO dto) {
		int result = 0;

		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) "
				+ "VALUES (?, ?, (SELECT mno FROM member WHERE mid=?), ?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid());// 나중에 사용자 이름 자동으로
			pstmt.setString(4, dto.getIp());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}

		return result;
	}

	public int delete(BoardDTO dto) {
		int result = 0;

		// conn
		// pstmt
		// sql

		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del='0' WHERE board_no = ? AND mno=(SELECT mno FROM member WHERE mid=?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}

		return result;
	}

	public int update(BoardDTO dto) {
		int result = 0;

		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title = ?, board_content = ?"
				+ " WHERE board_no = ? AND mno=(SELECT mno FROM member WHERE mid=?) ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int totalCount() {
		int result = 0;

		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}

		return result;
	}

	public List<CommentDTO> commentList(int no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM commentview WHERE board_no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setcno(rs.getInt("cno"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setComment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setMno(rs.getInt("mno")); // 뷰 만들어서 mname, mid 가져와야 함
				dto.setMid(rs.getString("mid"));
				dto.setMname(rs.getString("mname"));
				dto.setClike(rs.getInt("clike"));
				dto.setCip(Util.maskIp(rs.getString("cip")));
				list.add(dto);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con); 
			
		}
		
		return list;
	}

	
}
