package com.fresh.pojos;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "farmer")
public class Farmer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "farmer_id")
	private Integer farmerId;

	@Column(length = 20)
	private String firstname;

	@Column(length = 20)
	private String lastname;

	@Column(length = 50)
	private String email;

	@Column(name = "phone_no", length = 15, unique = true )
	private String phoneNo;

	@Column(length = 200)
	private String address;

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private List<OrderDetails> orderDetails;

	@OneToMany(mappedBy = "farmer1", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonInclude(Include.NON_NULL)
//	@JsonIgnore
	private List<StockDetails> stock = new ArrayList<StockDetails>();

	public Farmer() {
		System.out.println("Farmer Constructor invoked");
	}
	
	public Farmer(Integer farmerId, String firstname, String lastname, String email, String phone, String address) {
		super();
		this.farmerId = farmerId;			// *****
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNo = phone;
		this.address = address;
	}
	
	public Farmer(Integer farmerId, String firstname, String lastname) {
		super();
		this.farmerId = farmerId;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Integer getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Integer aFarmerId) {
		farmerId = aFarmerId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String aFirstname) {
		firstname = aFirstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String aLastname) {
		lastname = aLastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String aPhoneNo) {
		phoneNo = aPhoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String aAddress) {
		address = aAddress;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> aOrderDetails) {
		orderDetails = aOrderDetails;
	}

	public List<StockDetails> getStock() {
		return stock;
	}

	public void setStock(List<StockDetails> stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Farmer [farmerId=" + farmerId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", phoneNo=" + phoneNo + ", address=" + address + ", orderDetails=" + orderDetails
				+ ", stock=" + stock + "]";
	}

}
