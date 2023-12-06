package com.jsp.PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;			
import java.sql.SQLException;

public class FetchParticularRecord {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String qry = "Select * from self.student where id=?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			pstmt = con.prepareStatement(qry);// Compilation
			pstmt.setInt(1, 102);
			rs = pstmt.executeQuery();
			if	 (rs.next()) {
				int id = rs.getInt("id");
				String nm = rs.getString("name");
				int age = rs.getInt("age");

				System.out.println("Id is --> " + id);
				System.out.println("Name is --> " + nm);
				System.out.println("Age is --> " + age);
				System.out.println("");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
