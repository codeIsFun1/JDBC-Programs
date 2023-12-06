package com.jsp.PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Updation {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String qry = "Update  self.student set age=? where id=?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			pstmt = con.prepareStatement(qry);// compilation
			pstmt.setInt(1, 25);
			pstmt.setInt(2, 101);
			pstmt.executeUpdate();

			pstmt.setInt(1, 23);
			pstmt.setInt(2, 102);
			pstmt.executeUpdate();
			System.out.println("Updation Successfull");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
