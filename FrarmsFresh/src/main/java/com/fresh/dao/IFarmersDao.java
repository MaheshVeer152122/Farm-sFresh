package com.fresh.dao;

import java.util.List; 
import com.fresh.pojos.*;


public interface IFarmersDao {
	
	List<StockDetails> getAllProduct();
	List<Farmer> getAllFarmers();
	Farmer getFarmerDetails(int id);
	List<StockDetails> getFarmerStock(int farmerid);
	StockDetails getProductDetails(int farmerid, int productid);

}
