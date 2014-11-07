package controllers;

import java.util.ArrayList;
import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Warehouse extends Controller{
	public static Result index(){
		List<models.Warehouse> warehouse=new ArrayList<models.Warehouse>();
		return ok(warehouse.isEmpty()?"Warehouse not found":warehouse.toString());
	}
	
	public static Result detailsWarehouse(String warehouseId){
		models.Warehouse warehouse=models.Warehouse.find.where().eq("warehouseId", warehouseId).findUnique();
		return (warehouse==null?notFound("Not found"):ok(warehouse.toString()));
	}
	
	public static Result addNewWarehouse(){
		Form<models.Warehouse> warehouseForm=Form.form(models.Warehouse.class).bindFromRequest();
		if(warehouseForm.hasErrors()){
			return badRequest("Missing fields");
		}
		
		models.Warehouse warehouse=warehouseForm.get();
		warehouse.save();
		return ok(warehouse.toString());
	}
	
	public static Result deleteWarehouse(String warehouseId){
		models.Warehouse warehouse=models.Warehouse.find.where().eq("warehouseId", warehouseId).findUnique();
		if(warehouse!=null){
			warehouse.delete();
		}
		return ok();
	}
}
