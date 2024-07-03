package com.miniproject2.ecommerceapplication;

import java.sql.SQLException;
import java.util.Scanner;

public class Ecommerce {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Welcome to E-commerece based Application\r");
		System.out.println("User Operation\r");
		System.out.println("\t1.User Registration");
		System.out.println("\t2.User Login");
		System.out.println("\t3.User View Product item as sorted order");
		System.out.println("\t4.Buy Product");
		System.out.println("\t5.View cart");
		System.out.println("\t6.Purchase The Item");
		
		System.out.println("\rAdmin Operation\r");
	    System.out.println("\t7.Add Product Item");
		System.out.println("\t8.Calculate Bill");
		System.out.println("\t9.Display Amount to the End User");
		System.out.println("\t10.Check Quantity");
		System.out.println("\t11.Check Registered User");
		System.out.println("\t12.Check the perticular User History");
		
		System.out.println("\rGuest Operation\r");
		System.out.println("\t13.View Product Item");
		System.out.println("\t14.Not Purchase Item");
		
		User user=new User();
		Admin admin=new Admin();
		Guest guest=new Guest();
		String s = "Yes";
		while (s.equalsIgnoreCase("Yes")) {
			System.out.println("Enter your choice");
			Scanner scanner=new Scanner(System.in);
			int choice=scanner.nextInt();
			switch(choice)
			
			{
			case 1:
			    user.getUserRegistration();
				break;
			case 2:
				user.getUserLogin();
				break;
			case 3:
				user.getUserview();
				break;
			case 4:
				user.getBuyProduct();
				break;
			case 5:
				user.getViewCart();
				break;
			case 6:
				user.getPurchaseItem();
				break;
			case 7:
				admin.addProductItem();
			case 8:
				admin.calculateBill();
				break;
			case 9:
				admin.displayAmount();
				break;
			case 10:
				admin.checkQuantity();
				break;
			case 11:
				admin.checkRegisteredUser();
				break;
			case 12:
	 			admin.checkUseHistory();
	 			break;
	 		case 13:
	 			guest.ViewProductItem();
	 			break;
	 		default:
					System.out.println("invalid choice");
			}	
			System.out.println("If you want to continue then enter 'Yes' else enter anything");
			s = scanner.next();
		}
		
      System.out.println("Thanks for using our application");

	}
}
