//Insertion 
package org.jsp.DemoApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		String qry = "insert into btm.student values(105,'Abhi',67.70)";
		try {
			// first step of jdbc
			Class.forName("com.mysql.cj.jdbc.Driver");
			// second step established connection between java application and database
			// server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			// Third Step--> Create statement/platform
			stmt = con.createStatement();
			// fourth step--> Execute sql statement/queries
			int x = stmt.executeUpdate(qry);
			if (x > 0) {
				System.out.println("Insertion Successfull");
			} else {
				System.out.println("failed");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		// close all the costly resources
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
