package com.fresh.dao;

import java.util.List; 
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.fresh.pojos.Farmer;
import com.fresh.pojos.StockDetails;


@Repository
public class FarmersDaoImpl implements IFarmersDao {

	@PersistenceContext
	private EntityManager mgr;
	
	@Override
	public List<Farmer> getAllFarmers() {
		String jpql = "SELECT NEW com.fresh.pojos.Farmer(f.farmerId, f.firstname, f.lastname, f.email, f.phoneNo, f.address) FROM Farmer f";
		return mgr.createQuery(jpql, Farmer.class).getResultList();
	}

	@Override
	public List<StockDetails> getFarmerStock(int farmerid) {
		String jpql = "SELECT NEW com.fresh.pojos.StockDetails(sd.id, sd.stockItem, sd.pricePerUnit) FROM StockDetails sd JOIN sd.farmer1 f WHERE f.farmerId=:frmr";
		return mgr.createQuery(jpql, StockDetails.class).setParameter("frmr", farmerid).getResultList();
	}

	@Override
	public StockDetails getProductDetails(int farmerid, int productid) {
		String jpql = "SELECT NEW com.fresh.pojos.StockDetails(sd.id, sd.stockItem, sd.quantity, sd.pricePerUnit, sd.category) FROM StockDetails sd JOIN sd.farmer1 f WHERE f.farmerId=:frmr AND sd.id=:prdct";
		return mgr.createQuery(jpql, StockDetails.class).setParameter("frmr", farmerid).setParameter("prdct", productid).getSingleResult();
	}

	@Override
	public Farmer getFarmerDetails(int id) {
		String jpql = "SELECT NEW com.marketplace.pojos.Farmer(f.farmerId, f.firstname, f.lastname, f.email, f.phoneNo, f.address) FROM Farmer f WHERE f.farmerId=:fid";
		return mgr.createQuery(jpql, Farmer.class).setParameter("fid", id).getSingleResult();
	}

	@Override
	public List<StockDetails> getAllProduct() {
		String jpql = "SELECT NEW com.marketplace.pojos.StockDetails(s.id, s.stockItem, s.quantity, s.pricePerUnit, s.category, s.imagePath) FROM StockDetails s";
		return mgr.createQuery(jpql, StockDetails.class).getResultList();
	}
	
	

}
