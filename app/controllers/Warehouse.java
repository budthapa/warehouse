package controllers;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Warehouse extends Controller {
	public static Result index() {
		// List<models.Warehouse> warehouse=new ArrayList<models.Warehouse>();
		List<models.Warehouse> listWarehouse = models.Warehouse.find.all();
		return ok(listWarehouse.isEmpty() ? "Empty warehouse" : listWarehouse
				.toString());
	}

	public static Result detailsWarehouse(Long primaryKey) {
		// models.Warehouse
		// warehouse=models.Warehouse.find.where().eq("primaryKey",
		// primaryKey).findUnique();
		models.Warehouse warehouse = models.Warehouse.find.byId(primaryKey);

		Form<models.Warehouse> editWarehouseForm = Form.form(
				models.Warehouse.class).fill(warehouse);
		// return
		// (warehouse==null?notFound("Warehouse Not found"):ok(warehouse.toString()));
		return (warehouse == null ? notFound("Warehouse not found")
				: ok(warehouseEdit.render(primaryKey, editWarehouseForm)));
	}

	public static Result addNewWarehouse() {
		Form<models.Warehouse> warehouseForm = Form
				.form(models.Warehouse.class).bindFromRequest();
		if (warehouseForm.hasErrors()) {
			// return badRequest("Missing fields");
			return ok(warehouseCreate.render(warehouseForm));
		}
		System.out.println("warehouse");
		models.Warehouse warehouse = warehouseForm.get();
		warehouse.save();
		return ok(warehouse.toString());
	}

	public static Result deleteWarehouse(Long primaryKey) {
		models.Warehouse warehouseDelete = models.Warehouse.find
				.byId(primaryKey);
		if (warehouseDelete != null) {
			warehouseDelete.delete();
		}
		return redirect(routes.Application.index());
	}

	public static Result update(Long primaryKey) {
		Form<models.Warehouse> editWarehouseForm = Form.form(
				models.Warehouse.class).bindFromRequest();
		if (editWarehouseForm.hasErrors()) {
			return badRequest(warehouseEdit.render(primaryKey,
					editWarehouseForm));
		}
		editWarehouseForm.get().update(primaryKey);
		flash("Success", editWarehouseForm.get().warehouseName + "is updated");
		// return redirect(routes.Warehouse.detailsWarehouse(primaryKey));
		return redirect(routes.Application.index());

	}
}
