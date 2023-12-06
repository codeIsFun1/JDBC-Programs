package org.jsp.UpdationApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdationExample {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		String qry = "UPDATE btm.student SET perc=90.02 WHERE id=105";
		// First step --> load and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Second step--> establish the connection b/w java application and db server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			// Third Step-->create statement/platform
			stmt = con.createStatement();
			// Fourth step-->Execute sql statement/sql queries
			int x = stmt.executeUpdate(qry);
			if (x > 0) {
				System.out.println("Updation Successfull");
			} else
				System.out.println("Updation Failed");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		// Fifth Step-->Close all the costly resources
		finally {
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
