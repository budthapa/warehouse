package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Product extends Model{

	private static final long serialVersionUID = -7881391014358935700L;
	
	@Id
	public Long primaryKey;
	
	@Required
	public String productId;
	
	@Required
	public String productName;
	public String description;
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<Tag> tag=new ArrayList<>();
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	public List<StockItem> stockItems=new ArrayList<>();
	
	public Product(String productId, String productName, String description){
		this.productId=productId;
		this.productName=productName;
		this.description=description;
	}
	
	public static Finder<Long, Product> find=new Finder<>(Long.class, Product.class);
	
	public String toString(){
		return String.format("[Product %s %s %s]", productId, productName, description);
	}

	public static List<String> getProductNames(){
		List<String> productNames=new ArrayList<>();
		for(Product product : find.all()){
			productNames.add(product.productName);
		}
		return productNames;
	}
}
