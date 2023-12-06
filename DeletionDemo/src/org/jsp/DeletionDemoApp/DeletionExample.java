package org.jsp.DeletionDemoApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletionExample {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		String qry = "DELETE FROM BTM.STUDENT WHERE id=103";
		try {
			// First step of jdbc -> load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Second Step of jdbc -> establish connection b/w java application and db
			// server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			stmt = con.createStatement();
			int x = stmt.executeUpdate(qry);
			if (x > 0) {
				System.out.println("Deletion Succesfull");
			} else
				System.out.println("Deletion Failed");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
