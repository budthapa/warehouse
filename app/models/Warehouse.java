package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Warehouse extends Model {

	private static final long serialVersionUID = 6683783861039313432L;
	@Id
	public Long primaryKey;

	@Required
	public String warehouseId;

	@Required
	public String warehouseName;

	@OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
	public List<StockItem> stockItems = new ArrayList<>();

	public Warehouse(String warehouseId, String warehouseName) {
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
	}

	public static Finder<Long, Warehouse> find = new Finder<>(Long.class,
			Warehouse.class);

	public static List<String> getWarehouseNames() {
		List<String> warehouse = new ArrayList<>();
		for (Warehouse warehouseList : find.all()) {
			warehouse.add(warehouseList.warehouseName);
		}
		return warehouse;
	}
}
