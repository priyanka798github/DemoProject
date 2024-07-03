package com.miniproject2.ecommerceapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin implements AdminOperation{
	Scanner scanner = new Scanner(System.in);

	@Override
	public void addProductItem() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps = conn.prepareStatement("insert into product (ProductID,ProductName,ProductDescription,AvailableQuantity,price) values (?,?,?,?,?);");
			System.out.println("Product Id>>");
			int pId = scanner.nextInt();
			ps.setInt(1, pId);
			System.out.println("Product Name>>");
			String PName = scanner.next();
			ps.setString(2,PName);
			System.out.println("Product Description>>");
			String PDesc = scanner.next();
			ps.setString(3,PDesc);
			System.out.println("Quantity>>");
			int Pqty = scanner.nextInt();
			ps.setInt(4,Pqty);
			System.out.println("Price>>");
			int Pprice = scanner.nextInt();
			ps.setInt(5,Pprice);
			ps.execute();
			System.out.println("Product added successfully");
			ps = conn.prepareStatement("insert into  ProductTemp (UserName,ProductID,Quantity,price) values (?,?,?,?);" );
			ps.setString(1,"Admin");
			ps.setInt(2, pId);
			ps.setInt(3, Pqty);
			ps.setInt(4, Pprice);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
	}

	@SuppressWarnings("null")
	@Override
	public int calculateBill() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int bill = 0;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps = conn.prepareStatement("select productID,quantity,price from producttemp where UserName = ?");
			ps.setString(1,"Admin");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				 bill = bill + (rs.getInt(2) * rs.getInt(3));
				
			}
			
			ps = conn.prepareStatement("delete from producttemp where UserName = ? ");
			ps.setString(1,"Admin");
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		return bill;
		
	}

	@Override
	public void displayAmount() throws SQLException {
		Admin admin = new Admin();
		System.out.println("Display the amount to End User>> " + admin.calculateBill());
		
	}

	@Override
	public void checkQuantity() throws SQLException, ClassNotFoundException {
		
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps = conn.prepareStatement("select AvailableQuantity from product where ProductID = ?");
			System.out.println("Enter Product Id>>");
			ps.setInt(1, scanner.nextInt());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			   System.out.println("Quantity is>>" + rs.getInt(1));
			}else {
				throw new UserException("Product ID is not available");
			}
			
		}catch(UserException e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		
	}

	@Override
	public void checkRegisteredUser() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps = conn.prepareStatement("select * from user");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				System.out.println("Username>>" + rs.getString(4));
				System.out.println("First name>> " + rs.getString(2));
				System.out.println("Last name>>" + rs.getString(3));
				System.out.println("Email id>> " + rs.getString(7));
				System.out.println("Mobile>>" + rs.getString(8));
				System.out.println("City>>" + rs.getString(6));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		
	}

	@Override
	public void checkUseHistory() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/06aprjavabatch24?autoReconnect=true&useSSL=false", "root", "root");
			ps =conn.prepareStatement("select ProductID,ProductDescription,Quantity from userhist where UserName = ?");
			System.out.println("Enter the user name>> ");
			ps.setString(1, scanner.next());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Product id>>" + rs.getInt(1));
				System.out.println("Product Description>>" + rs.getString(2));
				System.out.println("Quantity>> " + rs.getInt(3));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			conn.close();
			ps.close();
		}
		
		
	}

}
