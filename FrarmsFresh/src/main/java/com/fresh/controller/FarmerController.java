package com.fresh.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.pojos.Farmer;
import com.fresh.pojos.StockDetails;
import com.fresh.service.IFarmersService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private IFarmersService f_service;

	@GetMapping("/list")
	public ResponseEntity<?> farmersList() {
		System.out.println("in getFarmersList");
		List<Farmer> list = f_service.getFarmersList();
		return new ResponseEntity<List<Farmer>>(list, HttpStatus.OK);
	}

	@GetMapping("/farmerdetails/{farmerid}")
	public ResponseEntity<?> getFarmerDetails(@PathVariable int farmerid) {
		return new ResponseEntity<Farmer>(f_service.getFarmerDetails(farmerid), HttpStatus.OK);
	}

	@GetMapping("/products/{farmerid}")
	public ResponseEntity<?> stockDetails(@PathVariable int farmerid) {
		List<StockDetails> products = f_service.getFarmerStock(farmerid);
		return new ResponseEntity<List<StockDetails>>(products, HttpStatus.OK);
	}

	@GetMapping("/products/{farmerid}/{productid}")
	public ResponseEntity<?> productDetails(@PathVariable int farmerid, @PathVariable int productid) {
		StockDetails product = f_service.getProductDetails(farmerid, productid);
		return new ResponseEntity<StockDetails>(product, HttpStatus.OK);
	}
	
	@GetMapping("/allproducts")
	public ResponseEntity<?> productlist() {
		System.out.println("in productlist");
		List<StockDetails> list = f_service.getAllProduct();
		return new ResponseEntity<List<StockDetails>>(list, HttpStatus.OK);
	}

}
