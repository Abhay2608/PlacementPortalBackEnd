package iiitb.placement_portal.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class AddAdminUtility{  

	public static void main(String args[]){  
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/placementportal","ooad","root");  

//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
		String query = "insert into admin(id,email,password)values(?,?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,14);
//	    preparedStmt.setString(2,passwordEncoder.encode("password"));
	    preparedStmt.setString(2,"roshni");
	    preparedStmt.setString(3,"password" );
	    preparedStmt.executeUpdate();
	    
	    con.close();  
	    System.out.println(" main class - Admin Insertion Successful ");
		}
	catch(Exception e){ 
		System.out.println(" main class - exception " + e);
		}  
	}  
}