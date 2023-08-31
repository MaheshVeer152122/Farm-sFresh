package com.fresh.service;

import java.util.List; 

import com.fresh.pojos.Cart;
import com.fresh.pojos.CartItem;
import com.fresh.pojos.OrderDetails;
import com.fresh.pojos.User;

public interface IUserService {
	
	public User Authenticate(String email, String password);
	public boolean Register(User user);
	public CartItem AddToCart(int productid, int qty);
	public boolean PlaceOrder(Cart cart, User user);
	public User getUserDetails(int userId);
	public List<OrderDetails> getOrder(int userId);
	
}
