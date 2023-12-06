package org.jsp.InsertMultipleRecordApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMultipleRecord {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		String qry1 = "insert into btm.student values(103,'Martin',70.26)";
		String qry2 = "insert into btm.student values(106,'Miller',70.26)";
		String qry3 = "insert into btm.student values(107,'Ford',70.26)";
		String qry4 = "insert into btm.student values(108,'Jones',70.26)";
		try {
			// First Step --> Load and Register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Second Step -->Established the connection b/w java Application nd Db server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			// Third Step --> Create Statement/platform
			stmt = con.createStatement();
			// Fourth Step--> Execute sql Statement/sql queries
			stmt.executeUpdate(qry1);
			stmt.executeUpdate(qry2);
			stmt.executeUpdate(qry3);
			stmt.executeUpdate(qry4);
			System.out.println("Insertion Successfull");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
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
