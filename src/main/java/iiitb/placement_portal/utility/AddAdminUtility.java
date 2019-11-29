package iiitb.placement_portal.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class AddAdminUtility{  

	public static void main(String args[]){  
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/placementportal","root","root");

		String query = "insert into admin(id,email,password)values(?,?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,4542);
	    preparedStmt.setString(2,"admin");
	    preparedStmt.setString(3,"admin" );
	    preparedStmt.executeUpdate();
	    
	    con.close();  
	    System.out.println(" main class - Admin Insertion Successful ");
		}
	catch(Exception e){ 
		System.out.println(" main class - exception " + e);
		}  
	}  
}