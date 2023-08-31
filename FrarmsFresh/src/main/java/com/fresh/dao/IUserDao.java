package com.fresh.dao;

import java.util.List; 

import com.fresh.pojos.*;


public interface IUserDao {
	
	public boolean RegisterUser(User user);
	public User AuthenticateUser(String email, String password);
	public CartItem AddToCart(int productid, int qty);
	public boolean PlaceOrder(Cart cart, User user);
	public User getUserDetails(int userId);
	public List<OrderDetails> getOrder(int userId);
	
}
