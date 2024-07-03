package com.miniproject2.ecommerceapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User implements UserOperation{
	String Uname = " ";
	Scanner scanner = new Scanner(System.in);

	@Override
	public void getUserRegistration() throws SQLException {
    	Connection conn = null;
		PreparedStatement ps = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
		ps = conn.prepareStatement("insert into user (firstName,lastName,userName,Password,City,MailId,MobileNo) values ( ?,?,?,?,?,?,?)");
		System.out.println("Enter the first name>>");
		ps.setString(1, scanner.next());
		System.out.println("Enter the last name>>");
		ps.setString(2, scanner.next());
		System.out.println("Enter the username>>");
		ps.setString(3, scanner.next());
		System.out.println("Enter the password>>");
		ps.setString(4, scanner.next());
		System.out.println("Enter the city>>");
		ps.setString(5, scanner.next());
		System.out.println("Enter the mail id>>");
		ps.setString(6, scanner.next());
		System.out.println("Enter the mobile number>>");
		ps.setString(7, scanner.next());
		ps.execute();
		System.out.println("You have done Registration successfully..");
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
		System.exit(0);
	}finally {
		conn.close();
		ps.close();
	}
	
		
		
	}

	@Override
	public void getUserLogin() throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps =conn.prepareStatement("select * from user where username = ? and Password = ? ");
			System.out.println("Enter the username>>");
			Uname = scanner.next();
			ps.setString(1, Uname );
			System.out.println("Enter the password>>");
			ps.setString(2, scanner.next());
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				throw new UserException("Incorrect username or password");
			}else {
				System.out.println("succuessfully logged in...");
				
			}
		}catch(UserException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		
		
		
	}

	@Override
	public void getUserview() throws SQLException {
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

	@SuppressWarnings("resource")
	@Override
	public void getBuyProduct() throws SQLException {
		
		getUserLogin();
	
		String str = "Yes";
		while (str.equalsIgnoreCase("Yes")) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps = conn.prepareStatement("insert into ProductTemp (UserName,ProductID,Quantity,price) values (?,?,?,?)");
			ps.setString(1, Uname);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.execute();
			ps =conn.prepareStatement("select * from product where ProductID = ?");
	    	System.out.println("Enter the product id to buy product");
			ps.setInt(1, scanner.nextInt());
			ResultSet rs =ps.executeQuery();
			if(rs.next()) {
			  System.out.println("Enter a quantity");
			  int quant = scanner.nextInt();
			  int availQuantity = rs.getInt(4);
			  if (quant<=availQuantity) {
				System.out.println("Do you want to view cart (Yes/No)");
				String s = scanner.next();
				if(s.equalsIgnoreCase("yes")) {
					System.out.println("Product Id>>" + rs.getInt(1));
					System.out.println("Product Name>>" + rs.getString(2));
					System.out.println("Product Description>>" + rs.getString(3));
					System.out.println("Available Quantity>>" + rs.getInt(4));
					System.out.println("Price>>" + rs.getInt(5));
					
				}
				
				ps = conn.prepareStatement("Update ProductTemp set ProductID = ?,Quantity = ? , price = ? where UserName = ? and ProductID = ?" );
				ps.setInt(1, rs.getInt(1));
				ps.setInt(2, quant);
				ps.setInt(3, rs.getInt(5));
				ps.setString(4, Uname);
				ps.setInt(5, 0);
				ps.executeUpdate();
				
			  
			   
			}else {
				 System.out.println("Product is currently unavailable..");
			 }
			
			}else {
				throw new UserException ("Invalid product ID..");
			}
			
		 }catch(Exception e){
	     	e.printStackTrace();
	     	System.exit(0);
	     }finally {
	 		conn.close();
			ps.close();
		}
		System.out.println("If you want to Add more products enter 'Yes' else enter anything");
		str = scanner.next();
		}
		
	}

	@Override
	public void getViewCart() {
		
		System.out.println("Product item has been added to cart");
		
	}

	@SuppressWarnings("resource")
	@Override
	public void getPurchaseItem() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
	    	ps =conn.prepareStatement("select productID,quantity,price from producttemp where UserName = ?");
			ps.setString(1, Uname);
			ResultSet rs = ps.executeQuery();
			int pID = 0;
			int quan = 0;
			int prce = 0;
			int bill = 0;
			int counter = 0;
			while(rs.next()) {
				 pID = rs.getInt(1);
				 quan = rs.getInt(2);
				 prce = rs.getInt(3);
				 bill = bill + (quan * prce);
				 counter ++;
				 ps = conn.prepareStatement("select * from product where ProductID = ?" );
				 ps.setInt(1, pID);
				 ResultSet rs1 = ps.executeQuery();
				 while (rs1.next()) {
					int qty = rs1.getInt(4);
					int Avlqty = qty - quan;
					ps = conn.prepareStatement("update product set AvailableQuantity = ? where ProductID = ?");
					ps.setInt(1, Avlqty);
					ps.setInt(2, pID);
					ps.execute();
					ps = conn.prepareStatement("delete from producttemp where UserName = ? ");
					ps.setString(1, Uname);
					ps.execute();
					ps = conn.prepareStatement("select ProductDescription from product where ProductID = ? ");
					ps.setInt(1, pID);
					ResultSet rs2 = ps.executeQuery();
					while(rs2.next()) {
						ps = conn.prepareStatement("insert into UserHist (UserName,ProductID,ProductDescription,Quantity) values (?,?,?,?)");
						ps.setString(1, Uname);
						ps.setInt(2, pID);
						ps.setString(3, rs2.getString(1));
						ps.setInt(4, quan);
						ps.execute();
					}
				 }
			}
            if(counter==0) {
				throw new UserException("No items are added by user");
			}
			
				System.out.println("Total Bill Amount>>" + bill );
				
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		
	}

	
	

}
