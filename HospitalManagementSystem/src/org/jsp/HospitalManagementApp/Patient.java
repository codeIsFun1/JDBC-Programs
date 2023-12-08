package org.jsp.HospitalManagementApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private Connection con;
	private Scanner sc;

	public Patient(Connection c, Scanner s) {
		this.con = c;
		this.sc = s;
	}

	public void addPatient() {

		System.out.println("Enter the Name of the patient");
		String nm = sc.next();
		System.out.println("Enter the Patient age");
		int age = sc.nextInt();
		System.out.println("Enter the gender");
		String g = sc.next();
		try {
			String qry = "Insert into hospital_db.patient (pname,age,gender) values(?,?,?)";
			pstmt = con.prepareStatement(qry);
			pstmt.setString(1, nm);
			pstmt.setInt(2, age);
			pstmt.setString(3, g);
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Insertion Done");
			} else
				System.out.println("Insertion Failed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void viewDetails() {
		String qry = "Select * from hospital_db.patient";
		try {
			pstmt = con.prepareStatement(qry);
			rs = pstmt.executeQuery();
			System.out.println("                                   Patient                                ");
			System.out.printf("%-20s %-20s %-20s %-10s%n", "Patien id", "Patient Name", "Age", "Gender");
			System.out.println("-----------------------------------------------------------------------");
			while (rs.next()) {
				int id = rs.getInt("pid");
				String name = rs.getString("pname");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");

				System.out.printf("%-20s %-20s %-20s %-10s%n", id, name, age, gender);
				System.out.println("-----------------------------------------------------------------------");
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

	public boolean getElementById(int id) {
		String qry = "Select * from hospital_db.patient where pid=?";
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
