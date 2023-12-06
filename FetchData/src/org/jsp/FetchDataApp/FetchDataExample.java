package org.jsp.FetchDataApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchDataExample {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String qry = "SELECT * FROM btm.student";

		try {
			// First step-->load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Second Step-->Established the connection b/w java Application and db Server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			// Third Step-->Create Statement/platform
			stmt = con.createStatement();
			// FourthStep-->Execute SQl Queries
			rs = stmt.executeQuery(qry);
			while (rs.next()) {
				int id = rs.getInt("id");
				String nm = rs.getString("name");
				double perc = rs.getDouble("perc");
				System.out.println("Id is --> " + id);
				System.out.println("Name is --> " + nm);
				System.out.println("Percentage is " + perc);
				System.out.println();
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
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
