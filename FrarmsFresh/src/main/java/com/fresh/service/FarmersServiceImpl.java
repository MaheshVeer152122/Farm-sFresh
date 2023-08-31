package com.fresh.service;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fresh.dao.IFarmersDao;
import com.fresh.pojos.Farmer;
import com.fresh.pojos.StockDetails;


@Service
@Transactional
public class FarmersServiceImpl implements IFarmersService {

	@Autowired
	private IFarmersDao f_dao;
	
	@Override
	public List<Farmer> getFarmersList() {
		return f_dao.getAllFarmers();
	}

	@Override
	public List<StockDetails> getFarmerStock(int farmerid) {
		return f_dao.getFarmerStock(farmerid);
	}

	@Override
	public StockDetails getProductDetails(int farmerid, int productid) {
		return f_dao.getProductDetails(farmerid, productid);
	}

	@Override
	public Farmer getFarmerDetails(int id) {
		return f_dao.getFarmerDetails(id);
	}

	@Override
	public List<StockDetails> getAllProduct() {
		return f_dao.getAllProduct();
	}
	
	
}
