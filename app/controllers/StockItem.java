package controllers;

import java.util.ArrayList;
import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class StockItem extends Controller {
	public static Result index(){
		List<models.StockItem> stockItem=new ArrayList<models.StockItem>();
		return ok(stockItem.isEmpty()?"No Stock Item":stockItem.toString());
	}
	
	public static Result detailsStockItem(String stockItemId){
		models.StockItem stockItem=models.StockItem.find.where().eq("stockItemId", stockItemId).findUnique();
		return (stockItem==null? notFound("Item not found"): ok(stockItem.toString()));
	}
	
	public static Result addNewStockItem(){
		Form<models.StockItem> stockItemForm=Form.form(models.StockItem.class).bindFromRequest();
		if(stockItemForm.hasErrors()){
			return badRequest("Missing required field");
		}
		
		models.StockItem stockItem=stockItemForm.get();
		stockItem.save();
		return ok(stockItem.toString());
	}
	
	public static Result deleteStockItem(String stockItemId){
		models.StockItem stockItem=models.StockItem.find.where().eq("stockItemId", stockItemId).findUnique();
		if(stockItem!=null){
			stockItem.delete();
		}
		return ok();
	}
}
