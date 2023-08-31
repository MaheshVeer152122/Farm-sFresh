package com.fresh.dao;

import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import jakarta.persistence.*;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fresh.custom_exceptions.ResourceNotFoundException;
import com.fresh.pojos.Category;
import com.fresh.pojos.Farmer;
import com.fresh.pojos.OrderDetails;
import com.fresh.pojos.StockDetails;
import com.fresh.pojos.User;


@Repository
public class AdminDaoImpl implements IAdminDao {

	@PersistenceContext
	private EntityManager mgr;
	
	@Autowired
	private IFarmersDao fdao;
	
	@Override
	public boolean AddFarmer(Farmer farmer) {
		for(StockDetails product : farmer.getStock()) {
			product.setFarmer(farmer);
			mgr.persist(product);
		}
		mgr.persist(farmer);
		return true;
	}

	@Override
	public boolean AddProduct(int farmerid, StockDetails product) {
		Farmer farmer = fdao.getFarmerDetails(farmerid);
		product.setFarmer(farmer);
		mgr.persist(product);
		return true;
	}
	
	@Override
	public boolean RemoveFarmer(int farmerId) {
		boolean success = false;
		Farmer f = mgr.find(Farmer.class, farmerId);
		List<StockDetails> s = f.getStock();
		for(StockDetails sd : s) {
			mgr.remove(sd);
		}
		
		mgr.remove(f);
		success = true;
		return success;
	}

	@Override
	public boolean RemoveProduct(int productid) {
		boolean success = false;
		StockDetails product = mgr.find(StockDetails.class, productid);
		mgr.remove(product);
		success = true;
		return success;
	}

	@Override
	public boolean UpdateFarmer(Farmer farmer) {
		mgr.unwrap(Session.class).update(farmer);
		return true;
	}

	@Override
	public boolean UpdateProduct(StockDetails product) {
		mgr.unwrap(Session.class).update(product);
		return true;
	}

	@Override
	public StockDetails GetProductDetails(int productid) {
		return mgr.find(StockDetails.class, productid);
	}

	@Override
	public Farmer GetFarmerDetails(int farmerid) {
		return mgr.find(Farmer.class, farmerid);
	}

	@Override
	public Category GetCategory(int catid) {
		return mgr.find(Category.class, catid);
	}
	
	@Override
	public boolean SetCategory(String category) {
		Category c = new Category();
		c.setCategoryName(category);
		mgr.persist(c);
		return true;
	}
	
	@Override
	public boolean RemoveCategory(int catid) {
		boolean success = false;
		Category c = mgr.find(Category.class, catid);
		mgr.remove(c);
		success = true;
		return success;
	}

	@Override
	public String saveImage(int productId, MultipartFile imgFile) throws IOException {     // Day22.2
		StockDetails s = mgr.find(StockDetails.class, productId);
		String path = imgFile.getOriginalFilename();
		System.out.println("path {}"+ path);
		s.setImagePath(path);
		Files.copy(imgFile.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		
		return "file copied";
	}

	@Override
	public byte[] restoreImage(int empId) throws IOException {
		StockDetails s = mgr.find(StockDetails.class, empId);
		String path = s.getImagePath();
		if (path != null)
			return Files.readAllBytes(Paths.get(path));
		throw new ResourceNotFoundException("Image not  yet assigned , for " + s.getStockItem());
			
	}
	
	@Override
	public List<Category> getAllCategory() {
		String jpql = "SELECT NEW com.marketplace.pojos.Category(c.categoryId, c.categoryName) FROM Category c";
		return mgr.createQuery(jpql, Category.class).getResultList();
	}
	
	@Override
	public List<OrderDetails> getAllOrders() {
		String jpql = "SELECT NEW com.marketplace.pojos.OrderDetails(o.id, o.orderItem, o.quantity, o.amount, o.orders) FROM OrderDetails o";
		return mgr.createQuery(jpql, OrderDetails.class).getResultList();
	}

	@Override
	public List<User> getAllUser() {
		String jpql = "SELECT NEW com.marketplace.pojos.User(u.userId, u.email, u.password, u.phoneNo, u.address,"
				+ "u.firstname, u.lastname, u.isadmin) FROM User u";
		return mgr.createQuery(jpql, User.class).getResultList();
	}
	
	@Override
	public boolean UpdateUser(User user) {
		mgr.unwrap(Session.class).update(user);
		return true;
	}

	
}
