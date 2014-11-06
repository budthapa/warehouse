package controllers;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Product extends Controller {
	public static Result index() {
		List<models.Product> products = models.Product.find.findList();

		return ok(products.isEmpty() ? "No products" : products.toString());
	}

	public static Result details(String productId) {
		models.Product product = models.Product.find.where()
				.eq("productId", productId).findUnique();

		// return not found if product not found else return ok with product
		return (product == null) ? notFound("No product found") : ok(product
				.toString());
	}

	public static Result newProduct() {
		// create a product form and bind a request variable to it
		Form<models.Product> productForm = Form.form(models.Product.class)
				.bindFromRequest();
		// validate the form
		if (productForm.hasErrors()) {
			return badRequest("Product Id or name is missing");
		}

		// form is ok, so make a product and save it.
		models.Product product = productForm.get();
		product.save();
		return ok(product.toString());

	}

	public static Result delete(String productId) {
		// find a product with unique id
		models.Product product = models.Product.find.where()
				.eq("productId", productId).findUnique();
		if (product != null) {
			product.delete();
		}

		return ok();
	}

}
