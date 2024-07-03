package com.miniproject2.ecommerceapplication;

import java.sql.SQLException;

public interface AdminOperation {
	public void addProductItem() throws SQLException;
	public int calculateBill() throws SQLException;
	public void displayAmount() throws SQLException;
	public void checkQuantity() throws SQLException, ClassNotFoundException;
	public void checkRegisteredUser() throws SQLException;
	public void checkUseHistory() throws SQLException;


}
