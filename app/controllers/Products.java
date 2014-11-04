package controllers;

import java.util.List;
import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.*;

public class Products extends Controller {

	// creating the form of Product class
	private static final Form<Product> productForm = Form.form(Product.class);

	// list all products
	public static Result list() {
		// find the list of products from Product class
		List<Product> products = Product.findAll();

		// response to browser
		return ok(list.render(products));
	}

	// show blank product form
	public static Result newProduct() {
		return ok(details.render(productForm));
	}

	// show product edit form
	public static Result details(String ean) {
		return ok(ean);

	}

	// save the product
	public static Result save() {
		return TODO;

	}

	public static Result index() {
		return TODO;
	}

}
