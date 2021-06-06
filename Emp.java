package emp_jdbc;

import java.sql.*;
import java.util.*;

public class Emp 
{
	
	
		public static void main(String args[])
		{  
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Statement st = null;
			Scanner sc = new Scanner(System.in);
 			
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		int choice = 0;
		do
		{
			System.out.println("1.Insert\n2.Display\n3.Update Age data\n4.Exit");
			choice = sc.nextInt();
			switch(choice) 
			{
			case 1:	{
					System.out.println("Enter Employee Name :");
					String name =sc.next();
					System.out.println("Enter Employee Age :");
					int age = sc.nextInt();
					System.out.println("Enter Employee Desig :");
					String desig = sc.next();
					System.out.println("Enter Employee Sal :");
					int sal = sc.nextInt();
					
					
						try 
						{
							con=DriverManager.getConnection("jdbc:mysql://localhost:3306/iprime_assignment","root","12345"); 
							String get_empid = "select coalesce((select max(empid)+1 from iprime_assignment.emp1),1) asgetid";
							st = con.createStatement();
							ps = con.prepareStatement("insert into emp1 values(?,?,?,?,?)" );
							rs= st.executeQuery(get_empid);
							rs.next();
							ps.setInt(1, rs.getInt("getid"));
							ps.setString(2,name);
							ps.setInt(3,age);
							ps.setString(4,desig);
							ps.setInt(5,sal);
							ps.executeUpdate();
							System.out.println("records inserted successfully");
						} catch(Exception e)
						{
							
							e.printStackTrace();
						}
						finally {
							try { 
								if(con!=null) con.close();
								if(st!=null) st.close();
								if(rs!=null) rs.close();
								if(ps!=null) ps.close();
								
							}catch(Exception e) {
								System.out.println("Error at close....");
								e.printStackTrace();
							}
						}
			}
						break;
			case 2:
			{
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/iprime_assignment","root","12345");  
					st = con.createStatement();
					rs = st.executeQuery("select * from emp1");
					while(rs.next()) {
						System.out.println("empid : "+rs.getInt("empid")+" name : "+rs.getString("empname")+" age : "+rs.getInt("age")+" designation : "+rs.getString("desig")+" salary :"+rs.getInt("sal"));
						}
				}catch(Exception e)
				{
					
					e.printStackTrace();
				}
				finally {
					try { 
						if(con!=null) con.close();
						if(st!=null) st.close();
						if(rs!=null) rs.close();
						if(ps!=null) ps.close();
						
					}catch(Exception e) {
						System.out.println("Error at close....");
						e.printStackTrace();
					}
				}
			}break;
			case 3:
			{
				try {
					System.out.println("Enter Employee Name :");
					String name =sc.next();
					System.out.println("Enter Employee Age :");
					int age = sc.nextInt();
					System.out.println("Enter Employee Desig :");
					String desig = sc.next();
				
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/iprime_assignment","root","12345");  
					ps = con.prepareStatement("update emp1 set age = ? where desig = ? and empname = ? ");
				
					ps.setInt(1,age);
					ps.setString(2,desig);
					ps.setString(3,name);
					 
					ps.executeUpdate();
					System.out.println("Age updated succesfully");

				}catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						if(con!=null) con.close();
						if(st!=null) st.close();
						if(ps!=null) ps.close();
						if(rs!=null) rs.close();
					}catch(Exception e)
					{
						System.out.println("errror at close");
					}
				}
				break;
				
				
			}
			case 4: break;
			default : System.out.println("invalid entry");
					break;
			}
			
		}while(choice!=4);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
}
	