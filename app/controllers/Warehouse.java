package controllers;

import java.util.ArrayList;
import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Warehouse extends Controller{
	public static Result index(){
		List<models.Warehouse> warehouse=new ArrayList<models.Warehouse>();
		return ok(warehouse.isEmpty()?"Empty warehouse":warehouse.toString());
	}
	
	public static Result detailsWarehouse(Long primaryKey){
//		models.Warehouse warehouse=models.Warehouse.find.where().eq("primaryKey", primaryKey).findUnique();
		models.Warehouse warehouse=models.Warehouse.find.byId(primaryKey);
		
		Form<models.Warehouse> editWarehouseForm=Form.form(models.Warehouse.class).fill(warehouse);
//		return (warehouse==null?notFound("Warehouse Not found"):ok(warehouse.toString()));
		return (warehouse==null?notFound("Warehouse not found"):ok(warehouseEdit.render(primaryKey, editWarehouseForm)));
	}
	
	public static Result addNewWarehouse(){
		Form<models.Warehouse> warehouseForm=Form.form(models.Warehouse.class).bindFromRequest();
		if(warehouseForm.hasErrors()){
//			return badRequest("Missing fields");
			return ok(warehouseCreate.render(warehouseForm));
		}
		System.out.println("warehouse");
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
	
	public static Result update(Long primaryKey){
		Form<models.Warehouse> editWarehouseForm=Form.form(models.Warehouse.class).bindFromRequest();
		if(editWarehouseForm.hasErrors()){
			return badRequest(warehouseEdit.render(primaryKey, editWarehouseForm));
		}
		editWarehouseForm.get().update(primaryKey);
		//check this, unable to update
//		warehouse.update(primaryKey);
//		return ok();
	    return redirect(routes.Application.index());

	}
}
