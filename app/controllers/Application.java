package controllers;

import java.util.ArrayList;
import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	//getting all the items
    public static Result index() {
    	List<models.Warehouse> warehouses=models.Warehouse.find.all();
    	List<models.Product> products=models.Product.find.all();
    	List<models.StockItem> stockItems=models.StockItem.find.all();
    	
        return ok(index.render(warehouses, products, stockItems));
    }

}
