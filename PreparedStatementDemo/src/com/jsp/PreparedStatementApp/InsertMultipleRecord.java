package com.jsp.PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMultipleRecord {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String qry = "insert into self.student values(?,?,?)";

		try {
			// Step 1-->Load and Register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Step 2-->Established connection b/w java Application and Db server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			pstmt = con.prepareStatement(qry);// compilation
			pstmt.setInt(1, 101);
			pstmt.setString(2, "ADAM");
			pstmt.setInt(3, 23);
			int x = pstmt.executeUpdate();// Execution

			pstmt.setInt(1, 102);
			pstmt.setString(2, "WARD");
			pstmt.setInt(3, 24);
			int y = pstmt.executeUpdate();// Execution

			pstmt.setInt(1, 103);
			pstmt.setString(2, "MILLER");
			pstmt.setInt(3, 22);
			int z = pstmt.executeUpdate();// Execution

			if (x > 0 && y > 0 && z > 0) {
				System.out.println("Insertion Successfull");
			} else {
				System.out.println("Insertion Failed");
			}
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
