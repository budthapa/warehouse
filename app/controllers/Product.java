package controllers;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.productCreate;

public class Product extends Controller {
	public static Result index() {
		List<models.Product> products = models.Product.find.findList();

		return ok(products.isEmpty() ? "No products to display" : products.toString());
	}

	public static Result detailsProduct(Long primaryKey) {
		models.Product product = models.Product.find.where()
				.eq("primaryKey", primaryKey).findUnique();

		// return not found if product not found else return ok with product
		return (product == null) ? notFound("No product found") : ok(product
				.toString());
	}

	public static Result addNewProduct() {
		// create a product form and bind a request variable to it
		Form<models.Product> productForm = Form.form(models.Product.class)
				.bindFromRequest();
		// validate the form
		if (productForm.hasErrors()) {
//			return badRequest("Product Id or name is missing");
			return badRequest(productCreate.render(productForm));
		}

		// form is ok, so make a product and save it.
		models.Product product = productForm.get();
		product.save();
		return ok(product.toString());

	}

	public static Result deleteProduct(String productId) {
		// find a product with unique id
		models.Product product = models.Product.find.where()
				.eq("productId", productId).findUnique();
		if (product != null) {
			product.delete();
		}

		return ok();
	}

}
