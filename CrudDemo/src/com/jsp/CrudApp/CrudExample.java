package com.jsp.CrudApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CrudExample {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String qry1 = "insert into self.student values(?,?,?)";
		String qry2 = "delete from self.student where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=admin");

			boolean end = true;
			while (end) {
				sc = new Scanner(System.in);
				System.out.println("Select CRUD operations");
				System.out.println("1. Insert");
				System.out.println("2. Delete");
				System.out.println("3. Update");
				System.out.println("4. Display");
				System.out.println("5. Exit");
				int choice;

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input for choice. Please enter a valid integer.");
                    sc.next(); // Consume the invalid input to avoid an infinite loop
                }
                choice = sc.nextInt();
				switch (choice) {
				case 1:
					pstmt = con.prepareStatement(qry1);
					System.out.println("Enter id, name, age");
					 int id;
                     while (!sc.hasNextInt()) {
                         System.out.println("Invalid input for id. Please enter a valid integer.");
                         sc.next(); // Consume the invalid input to avoid an infinite loop
                     }
                     id = sc.nextInt();
                     sc.nextLine(); 
                     String nm;
                     while (!sc.hasNextLine()) {
                         System.out.println("Invalid input for name. Please enter a valid string.");
                         sc.next(); // Consume the invalid input to avoid an infinite loop
                     }
                     nm = sc.nextLine();
                     int age;
                     while (!sc.hasNextInt()) {
                         System.out.println("Invalid input for age. Please enter a valid integer.");
                         sc.next(); // Consume the invalid input to avoid an infinite loop
                     }
                     age = sc.nextInt();
					pstmt.setInt(1, id);
					pstmt.setString(2, nm);
					pstmt.setInt(3, age);
					int x = pstmt.executeUpdate();
					if (x > 0) {
						System.out.println("Insertion Successful");
					} else {
						System.out.println("Insertion Failed");
					}
					break;
				case 2:
					pstmt = con.prepareStatement(qry2);
					System.out.println("Enter the id");
					int i;
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input for id. Please enter a valid integer.");
                        sc.next(); // Consume the invalid input to avoid an infinite loop
                    }
                    i = sc.nextInt();
                    sc.nextLine();
					pstmt.setInt(1, i);
					int z = pstmt.executeUpdate();
					if (z > 0) {
						System.out.println("Deletion Successfull");
					} else {
						System.out.println("Deletion Failed");
					}
					break;
				case 3:
					boolean e = true;
					while (e) {
						System.out.println("1.update name");
						System.out.println("2.update age");
						System.out.println("3.Exit");
						int w;
	                     while (!sc.hasNextInt()) {
	                         System.out.println("Invalid input for id. Please enter a valid integer.");
	                         sc.next(); // Consume the invalid input to avoid an infinite loop
	                     }
	                     w = sc.nextInt();
	                     sc.nextLine();
						switch (w) {
						case 1:
							String qry3 = "update self.student set name=? where id=?";
							System.out.println("Enter the id whose name to be updated?");
							int q;
		                     while (!sc.hasNextInt()) {
		                         System.out.println("Invalid input for id. Please enter a valid integer.");
		                         sc.next(); // Consume the invalid input to avoid an infinite loop
		                     }
		                     q = sc.nextInt();
		                     sc.nextLine();
							System.out.println("Enter the name to be Updated");
							 String sn;
		                        while (!sc.hasNextLine()) {
		                            System.out.println("Invalid input for name. Please enter a valid string.");
		                            sc.next(); // Consume the invalid input to avoid an infinite loop
		                        }
		                        sn = sc.nextLine();
							pstmt = con.prepareStatement(qry3);
							pstmt.setString(1, sn);
							pstmt.setInt(2, q);
							int up = pstmt.executeUpdate();
							if (up > 0) {
								System.out.println("Name Updated");
							} else {
								System.out.println("Updation Failed");
							}
							break;
						case 2:
							String qry4 = "update self.student set age=? where id=?";
							System.out.println("Enter the id whose age to be updated?");
							int q1;
	                        while (!sc.hasNextInt()) {
	                            System.out.println("Invalid input for id. Please enter a valid integer.");
	                            sc.next(); // Consume the invalid input to avoid an infinite loop
	                        }
	                        q1 = sc.nextInt();
	                        sc.nextLine();
							System.out.println("Enter the age to be Updated");
							int o;
	                        while (!sc.hasNextInt()) {
	                            System.out.println("Invalid input for id. Please enter a valid integer.");
	                            sc.next(); // Consume the invalid input to avoid an infinite loop
	                        }
	                        o = sc.nextInt();
	                        sc.nextLine();
							pstmt = con.prepareStatement(qry4);
							pstmt.setInt(1, o);
							pstmt.setInt(2, q1);
							int a = pstmt.executeUpdate();
							if (a > 0) {
								System.out.println("Age Updated");
							} else {
								System.out.println("Updation Failed");
							}
							break;
						case 3:
							System.out.println("Visit Again!");
							e = false;
							break;
						default:
							System.out.println("Invalid Input");
							break;
						}
					}

					break;
				case 4:
					boolean l = true;
					while (l) {
						System.out.println("1.Print all the data inside the table");
						System.out.println("2.Print id");
						System.out.println("3.Print name");
						System.out.println("4.Print age");
						System.out.println("5 Exit");
						int d;
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input for id. Please enter a valid integer.");
                            sc.next(); // Consume the invalid input to avoid an infinite loop
                        }
                        d = sc.nextInt();
                        sc.nextLine();
						switch (d) {
						case 1:
							String qry5="Select * from self.student";
							pstmt=con.prepareStatement(qry5);
							rs=pstmt.executeQuery();
							while (rs.next()) {
								int gi=rs.getInt("id");
								String gn=rs.getString("name");
								int ga=rs.getInt("age");
								System.out.println("Id is --> "+gi);
								System.out.println("Name is --> "+gn);
								System.out.println("Age is --> "+ga);
								System.out.println();
							}
							break;
						case 2:
							String qry6="select id from self.student where name=?";
							pstmt=con.prepareStatement(qry6);
							System.out.println("Enter the name");
							String sn;
	                        while (!sc.hasNextLine()) {
	                            System.out.println("Invalid input for name. Please enter a valid string.");
	                            sc.next(); // Consume the invalid input to avoid an infinite loop
	                        }
	                        sn = sc.nextLine();
							pstmt.setString(1, sn);
							rs=pstmt.executeQuery();
							while (rs.next()) {
								int gi=rs.getInt("id");
								System.out.println("For "+sn+" Id is --> "+gi);
							}
							break;
						case 3:
							String qry7="Select name from self.student where id=?";
							pstmt=con.prepareStatement(qry7);
							System.out.println("Enter id");
							int a;
	                        while (!sc.hasNextInt()) {
	                            System.out.println("Invalid input for id. Please enter a valid integer.");
	                            sc.next(); // Consume the invalid input to avoid an infinite loop
	                        }
	                        a = sc.nextInt();
	                        sc.nextLine(); 
							pstmt.setInt(1, a);
							rs=pstmt.executeQuery();
							while (rs.next()) {
								String n=rs.getString("name");
								System.out.println("For id "+a+" name is -->"+n);
							}
							break;
						case 4:
							String qry8="Select age from self.student where id=?";
							pstmt=con.prepareStatement(qry8);
							System.out.println("Enter id");
							int c;
	                        while (!sc.hasNextInt()) {
	                            System.out.println("Invalid input for id. Please enter a valid integer.");
	                            sc.next(); // Consume the invalid input to avoid an infinite loop
	                        }
	                        c = sc.nextInt();
	                        sc.nextLine(); 
							pstmt.setInt(1, c);
							rs=pstmt.executeQuery();
							while (rs.next()) {
								int n=rs.getInt("age");
								System.out.println("For id "+c+" age is -->"+n);
							}
							break;
						case 5:
							l=false;
							break;
						default:
							System.out.println("Invalid Input");
							break;
						}
					}
					break;
				case 5:
					System.out.println("Thanks, Visit Again");
					end = false;
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
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
}
