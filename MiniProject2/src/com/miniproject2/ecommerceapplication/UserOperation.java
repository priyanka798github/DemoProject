
package com.miniproject2.ecommerceapplication;

import java.sql.SQLException;

public interface UserOperation {
	
	public void getUserRegistration() throws SQLException;
    public void getUserLogin() throws SQLException;
	public void getUserview() throws SQLException;
	public void getBuyProduct() throws SQLException;
	public void getViewCart(); 
	public void getPurchaseItem() throws SQLException; 

}
