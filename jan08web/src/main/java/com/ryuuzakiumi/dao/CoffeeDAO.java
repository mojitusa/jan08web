package com.ryuuzakiumi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryuuzakiumi.db.DBConnection;
import com.ryuuzakiumi.dto.CoffeeDTO;

public class CoffeeDAO extends AbstractDAO {

	public int selectBeverage(CoffeeDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO cafe (hotOrCold, beverage, tea) VALUES (?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getHotOrCold());
			pstmt.setString(2, dto.getBeverage());
			pstmt.setString(3, dto.getTeaChoice());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public List<CoffeeDTO> vieworder() {
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT hotOrCold, beverage, tea, COUNT(*) FROM cafe GROUP BY beverage, tea, hotOrCold";
		ResultSet rs = null;
		List<CoffeeDTO> list = null;
		
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<CoffeeDTO>();
			
			while (rs.next()) {
				CoffeeDTO cdto = new CoffeeDTO();
				cdto.setHotOrCold(rs.getString("hotOrCold"));
				cdto.setBeverage(rs.getString("beverage"));
				cdto.setTeaChoice(rs.getString("tea"));
				cdto.setCount(rs.getInt(4));
				
				list.add(cdto);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	
	}
	

}
