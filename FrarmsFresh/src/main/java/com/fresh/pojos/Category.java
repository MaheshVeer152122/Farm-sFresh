package com.fresh.pojos;

import java.io.Serializable; 
import java.util.List;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "category_name", unique = true)
	private String categoryName;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<StockDetails> stockDetails;

	public Category() {
		System.out.println("Category Constructor invoked");
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	
	public Category(Integer categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Category(Integer categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public void setCategoryId(Integer aCategoryId) {
		categoryId = aCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String aCategoryName) {
		categoryName = aCategoryName;
	}

	public List<StockDetails> getStockDetails() {
		return stockDetails;
	}

	public void setStockDetails(List<StockDetails> aStockDetails) {
		stockDetails = aStockDetails;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", stockDetails="
				+ stockDetails + "]";
	}

}
