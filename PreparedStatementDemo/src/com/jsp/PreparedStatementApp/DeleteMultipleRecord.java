package com.jsp.PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMultipleRecord {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String qry = "Delete from self.student where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			pstmt = con.prepareStatement(qry);// compilation

			pstmt.setInt(1, 101);
			pstmt.executeUpdate();// Execution
			pstmt.setInt(1, 102);
			pstmt.executeUpdate();// Execution
			pstmt.setInt(1, 103);
			pstmt.executeUpdate();// Execution
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}

		finally {
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
