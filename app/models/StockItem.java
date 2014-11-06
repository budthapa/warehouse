package models;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

public class StockItem extends Model {

	private static final long serialVersionUID = 705189973737179533L;
	
	@Id
	public Long primaryKey;
	
	@Required
	public String stockItemId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Warehouse warehouse;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Product product;
	public Long quantity;
	
	public StockItem(Warehouse warehouse, Product product, Long quantity){
		this.warehouse=warehouse;
		this.product=product;
		this.quantity=quantity;
	}
	
	public static Finder<Long, StockItem> find=new Finder<>(Long.class, StockItem.class);

}
