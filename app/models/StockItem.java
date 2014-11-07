package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class StockItem extends Model {

	private static final long serialVersionUID = 705189973737179533L;

	@Id
	public Long primaryKey;

	@Required
	public String stockItemId;

	@Transient
	public String productName;
	
	@Transient
	public String warehouseName;


	@ManyToOne(cascade = CascadeType.PERSIST)
	public Warehouse warehouse;

	@ManyToOne(cascade = CascadeType.PERSIST)
	public Product product;
	@Required
	public Long quantity;

	public StockItem(Warehouse warehouse, Product product, Long quantity) {
		this.warehouse = warehouse;
		this.product = product;
		this.quantity = quantity;
	}

	public static Finder<Long, StockItem> find = new Finder<>(Long.class,
			StockItem.class);

}
