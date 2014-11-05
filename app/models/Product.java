package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Constraints;

public class Product {
	@Constraints.Required
	public String ean;
	@Constraints.Required
	public String name;
	public String description;

	public Product() {
	}

	public Product(String ean, String name, String description) {
		this.ean = ean;
		this.name = name;
		this.description = description;
	}

	public String tostring() {
		return String.format("%s - %s", ean, name);
	}

	private static List<Product> products;

	//testing git
	
	static {
		products = new ArrayList<>();
		products.add(new Product("11", "name 1", "desc 1"));
		products.add(new Product("12", "name 2", "desc 2"));
		products.add(new Product("13", "name 3", "desc 3"));

	}

	public static List<Product> findAll() {
		return new ArrayList<Product>(products);
	}

	public static Product findByEan(String ean) {
		for (Product prd : products) {
			if (prd.equals(ean)) {
				return prd;
			}
		}
		return null;
	}
	
	public static List<Product> findByName(String name){
	    final List<Product> results = new ArrayList<>();
		for(Product prd:products){
			if(prd.name.toLowerCase().contains(name)){
				results.add(prd);
			}
		}
		return results;
	}
	
	public static boolean remove(Product product){
		return products.remove(product);
	}
	
	public void save() {
	    products.remove(findByEan(this.ean));
	    products.add(this);
	  }

}
