package com.ryuuzakiumi.dao;
// 부모 DAO = DBconn, close

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ryuuzakiumi.db.DBConnection;

public class AbstractDAO {

	DBConnection db = DBConnection.getInstance();

	public void logwrite(String ip, String url, String data) {
		Connection con =  db.getConnection();
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO iplog (iip, iurl, idata) VALUES (?,?,?)";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, url);
			pstmt.setString(3, data);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	protected void close(ResultSet rs, PreparedStatement pstmt, Connection con) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
