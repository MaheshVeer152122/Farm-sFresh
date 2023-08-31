package com.fresh.service;

import java.util.List; 
import com.fresh.pojos.Farmer;
import com.fresh.pojos.StockDetails;

public interface IFarmersService {
	
	List<Farmer> getFarmersList();
	Farmer getFarmerDetails(int id);
	List<StockDetails> getFarmerStock(int farmerid);
	StockDetails getProductDetails(int farmerid, int productid);
	List<StockDetails> getAllProduct();
	
}
