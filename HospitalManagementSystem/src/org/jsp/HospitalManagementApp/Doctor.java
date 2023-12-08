package org.jsp.HospitalManagementApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private Connection con;

	public Doctor(Connection c) {
		this.con = c;
	}

	

	public void viewDoctors() {
		String qry = "Select * from hospital_db.doctor";
		try {
			pstmt = con.prepareStatement(qry);
			rs = pstmt.executeQuery();
			System.out.println("                   Doctors                ");
			System.out.printf("%-20s %-20s %-20s%n", "Doctor id", "Doctor Name","Specialization");
			System.out.println("------------------------------------------------------");
			while (rs.next()) {
				int id = rs.getInt("did");
				String name = rs.getString("dname");
				String spec = rs.getString("specialization");

				System.out.printf("%-20s %-20s %-20s%n", id, name, spec);
				System.out.println("------------------------------------------------------");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}

	}

	public boolean checkDoctor(int id) {
		String qry = "Select * from hospital_db.doctor where did=?";
		try {
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}
		return false;
		
	}
}
