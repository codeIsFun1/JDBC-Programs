package com.jsp.PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginValidation {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the UserName");
		String un=sc.next();
		System.out.println("Enter the Password");
		String pw=sc.next();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry="Select name from btm.user where uname=? and password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			pstmt=con.prepareStatement(qry);
			pstmt.setString(1, un);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				String nm=rs.getString("name");
				System.out.println("Welcome "+nm);
			}
			else {
				System.out.println("Invalid username and password");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			sc.close();
		}
	}

}
