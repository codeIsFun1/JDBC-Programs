package org.jsp.HospitalManagementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HMS {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");
			Patient p = new Patient(con, sc);
			Doctor d = new Doctor(con);
			boolean end = true;
			while (end) {
				System.out.println("Hospital Management System");
				System.out.println("1.Patient");
				System.out.println("2.Doctor");
				System.out.println("3.Appointment");
				System.out.println("4.Exit");
				System.out.println("Enter your choice");
				int choice;
				while (!sc.hasNextInt()) {
					System.out.println("Invalid input for choice. Please enter a valid integer.");
					sc.next(); // Consume the invalid input to avoid an infinite loop
				}
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					boolean e = true;
					while (e) {
						System.out.println("Patient");
						System.out.println("1.Add patient");
						System.out.println("2.View patient");
						System.out.println("3.Check Patient");
						System.out.println("4.Exit");
						System.out.println("Enter your choice");
						int c;
						while (!sc.hasNextInt()) {
							System.out.println("Invalid input for choice. Please enter a valid integer.");
							sc.next(); // Consume the invalid input to avoid an infinite loop
						}
						c = sc.nextInt();
						switch (c) {
						case 1:
							p.addPatient();
							break;

						case 2:
							p.viewDetails();
							break;
						case 3:
							System.out.println("Enter the id");
							int i;
							while (!sc.hasNextInt()) {
								System.out.println("Invalid input for choice. Please enter a valid integer.");
								sc.next(); // Consume the invalid input to avoid an infinite loop
							}
							i = sc.nextInt();
							System.out.println("Patient are there -->" + p.getElementById(i));
							break;

						case 4:
							System.out.println("Thanks,Visit Again");
							e = false;
							break;
						default:
							System.out.println("Invalid Input");
							break;
						}
					}
					break;
				case 2:
					boolean s = true;
					while (s) {
						System.out.println("Doctor");
						System.out.println("1.View Doctors");
						System.out.println("2.Check Doctor");
						System.out.println("3.Exit");
						System.out.println("Enter your choice");
						int c;
						while (!sc.hasNextInt()) {
							System.out.println("Invalid input for choice. Please enter a valid integer.");
							sc.next(); // Consume the invalid input to avoid an infinite loop
						}
						c = sc.nextInt();
						switch (c) {
						case 1:
							d.viewDoctors();
							break;
						case 2:
							System.out.println("Enter the id");
							int i;
							while (!sc.hasNextInt()) {
								System.out.println("Invalid input for choice. Please enter a valid integer.");
								sc.next(); // Consume the invalid input to avoid an infinite loop
							}
							i = sc.nextInt();
							System.out.println("Doctor is present -->" + d.checkDoctor(i));
							break;
						case 3:
							System.out.println("Thanks,Visit Again");
							s = false;
						default:
							System.out.println("Invalid Input");
							break;
						}
					}
					break;
				case 3:
					Appointment(p, d, sc, con, pstmt);
					break;
				case 4:
					System.out.println("Thanks,Visit Again");
					end = false;
					break;
				default:
					System.out.println("Invalid input");
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (sc != null) {
				try {
					sc.close();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

	public static void Appointment(Patient p, Doctor d, Scanner sc, Connection con, PreparedStatement pstmt) {
		System.out.println("Enter Patient ID");
		int pid;
		while (!sc.hasNextInt()) {
			System.out.println("Invalid input for choice. Please enter a valid integer.");
			sc.next(); // Consume the invalid input to avoid an infinite loop
		}
		pid = sc.nextInt();
		System.out.println("Enter Doctor ID");
		int did;
		while (!sc.hasNextInt()) {
			System.out.println("Invalid input for choice. Please enter a valid integer.");
			sc.next(); // Consume the invalid input to avoid an infinite loop
		}
		did = sc.nextInt();
		System.out.println("Enter the appointment date (yyyy-mm-dd):");
		String dd;
		sc.nextLine(); // Consume the newline character
		while (true) {
			dd = sc.nextLine();
			if (!dd.isEmpty()) {
				break;
			} else {
				System.out.println("Invalid input for date. Please enter a valid date (yyyy-mm-dd):");
			}
		}
		if (p.getElementById(pid) && d.checkDoctor(did)) {
			if (checkDoctorAvailability(did, dd, con, pstmt)) {
				String appointmentQuery = "INSERT INTO hospital_db.appointments(pid, did, app_date) VALUES(?, ?, ?)";
				try {
					pstmt = con.prepareStatement(appointmentQuery);
					pstmt.setInt(1, pid);
					pstmt.setInt(2, did);
					pstmt.setString(3, dd);
					int rowsAffected = pstmt.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("Appointment Booked!");
					} else {
						System.out.println("Failed to Book Appointment!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (Exception e) {
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

			} else {
				System.out.println("Doctor not available on this date!!");
			}
		} else {
			System.out.println("Either doctor or patient doesn't exist!!!");
		}
	}

	public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection con,
			PreparedStatement pstmt) {
		String query = "SELECT COUNT(*) FROM hospital_db.appointments WHERE did = ? AND app_date = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, doctorId);
			pstmt.setString(2, appointmentDate);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				if (count == 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
