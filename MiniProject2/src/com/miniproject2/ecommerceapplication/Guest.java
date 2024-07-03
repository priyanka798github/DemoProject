package com.miniproject2.ecommerceapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Guest implements GuestOperation {

	@Override
	public void ViewProductItem() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps =conn.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Product Id>>" + rs.getInt(1));
				System.out.println("Product Name>>" + rs.getString(2));
				System.out.println("Product Description>>" + rs.getString(3));
				System.out.println("Available Quantity>>" + rs.getInt(4));
				System.out.println("Price>>" + rs.getInt(5));
			}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		
	}

}
