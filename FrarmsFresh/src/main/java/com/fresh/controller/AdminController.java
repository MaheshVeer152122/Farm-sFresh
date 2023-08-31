package com.fresh.controller;

import java.io.IOException; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fresh.pojos.Category;
import com.fresh.pojos.Farmer;
import com.fresh.pojos.OrderDetails;
import com.fresh.pojos.StockDetails;
import com.fresh.pojos.User;
import com.fresh.service.IAdminService;
import com.fresh.service.IFarmersService;
import com.fresh.service.IUserService;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService service;
	
	@Autowired
	private IFarmersService f_service;
	
	@Autowired
	private IUserService u_service;

	@PostMapping("/newfarmer")
	public ResponseEntity<?> AddNewFarmer(@RequestBody Farmer farmer) {
		System.out.println(farmer.getStock());
		service.AddFarmer(farmer);
		return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
	}
	
	@PostMapping("/newproduct/{farmerid}")
	public ResponseEntity<?> AddNewProduct(@PathVariable int farmerid, @RequestBody StockDetails product) {
		service.AddProduct(farmerid, product);
		return new ResponseEntity<String>("Product Added Successfully", HttpStatus.OK);
	}
	
	@PostMapping("/{productid}/image")
	public ResponseEntity<?> uploadImage(@PathVariable int productid, @RequestParam MultipartFile imgFile)
			throws IOException {
		System.out.println(imgFile);
		System.out.println("product id " + productid);
		System.out.println("uploaded file name :  " + imgFile.getOriginalFilename() + " size " + imgFile.getSize());
		String msg = service.saveImage(productid, imgFile);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{productid}", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE }) 
	public ResponseEntity<?> downloadImage(@PathVariable int productid)  throws IOException{
		System.out.println("in img download " + productid);
		return ResponseEntity.ok(service.restoreImage(productid));
	}
	
	@GetMapping("/removefarmer/{farmerid}")
	public ResponseEntity<?> DeleteFarmer(@PathVariable int farmerid) {
		service.RemoveFarmer(farmerid);
		return new ResponseEntity<String>("Farmer Removed Successfully", HttpStatus.OK);
	}
	
	@GetMapping("removeproduct/{productid}")
	public ResponseEntity<?> DeleteProduct(@PathVariable int productid) {
		service.RemoveProduct(productid);
		return new ResponseEntity<String>("Product Removed Successfully", HttpStatus.OK);
	}

	@PutMapping("updateproduct/{productid}")
	public ResponseEntity<?> UpdateProduct(@PathVariable int productid, @RequestParam("stockitem") String stockitem,
			@RequestParam("priceperunit") float priceperunit, @RequestParam("catid") int catid, 
			@RequestParam("quantity") int quantity) {
		StockDetails product = service.GetProductDetails(productid);
		if (product != null) {
			Category category = service.GetCategory(catid);
			product.setPricePerUnit(priceperunit);
			product.setStockItem(stockitem);
			product.setCategory(category);
			product.setQuantity(quantity);
			service.UpdateProduct(product);
			return new ResponseEntity<String>("Product Updated", HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("updatefarmer/{farmerid}")
	public ResponseEntity<?> UpdateFarmer(@PathVariable int farmerid, @RequestBody Farmer f) {
		System.out.println("RequestBody : " + f);
		Farmer farmer = f_service.getFarmerDetails(farmerid);
		System.out.println("old farmer : " + farmer);
		if (farmer != null) {
			farmer.setFirstname(f.getFirstname());
			farmer.setLastname(f.getLastname());
			farmer.setEmail(f.getEmail());
			farmer.setAddress(f.getAddress());
			farmer.setPhoneNo(f.getPhoneNo());
			service.UpdateFarmer(farmer);
			return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/addcategory/{category}")
	public ResponseEntity<?> AddCategory(@PathVariable String category){
		service.SetCategory(category);
		return new ResponseEntity<String>("New Category: "+category+" Added", HttpStatus.OK);
	}
	
	@GetMapping("/removecategory/{catid}")
	public ResponseEntity<?> removeCategory(@PathVariable int catid){
		service.RemoveCategory(catid);
		return new ResponseEntity<String>("Category Removed", HttpStatus.OK);
	}
	
	@GetMapping("/categorylist")
	public ResponseEntity<?> categorylist() {
		System.out.println("in categorylist");
		List<Category> list = service.getAllCategory();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/allorders")
	public ResponseEntity<?> getAllOrders(){
		System.out.println("in getAllOrders");
		List<OrderDetails> list = service.getAllOrders();
		return new ResponseEntity<List<OrderDetails>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUsers(){
		System.out.println("in getAllUsers");
		List<User> list = service.getAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/updateuser/{userId}")
	public ResponseEntity<?> UpdateUser(@PathVariable int userId, @RequestBody User user) {
		System.out.println("inside UpdateUser" + userId);
		User u = u_service.getUserDetails(userId);
		if (u != null) {
			u.setFirstname(user.getFirstname());
			u.setLastname(user.getLastname());
			u.setEmail(user.getEmail());
			u.setAddress(user.getAddress());
			u.setPhoneNo(user.getPhoneNo());
			service.UpdateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/userdetails/{userId}")
	public ResponseEntity<?> getUserDetails(@PathVariable int userId){
		System.out.println("in getUserDetails");
		User u = u_service.getUserDetails(userId);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@GetMapping(value = "/image/{productName}", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE }) 
	public ResponseEntity<?> downloadImageAgain(@PathVariable String productName)  throws IOException{
		System.out.println("in img download " + productName);
		return ResponseEntity.ok(service.restoreImageAgain(productName));
	}
}
