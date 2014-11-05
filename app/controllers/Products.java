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
		//get the product details by its id
		final Product product=Product.findByEan(ean);
		if(product==null){
			return notFound(String.format("Item %s not found", ean));
		}
		//fill the empty form with the details found above
		Form<Product> filledForm=productForm.fill(product);
		return ok(details.render(filledForm));
		
	}

	// save the product
	/* Flash scope is a place where we can store variables between requests. 
	 * Everything in flash scope is there until the following request, at which point it’s deleted
	 * Add flash in main.scala.html template in body part because every page extends it
	 */
	public static Result save() {
		//getting the form data from details.scala.html
		Form<Product> getFormData=productForm.bindFromRequest();
		
		System.out.println("error here");
		//check for errors like validation
		if(getFormData.hasErrors()){
			//display message
			flash("error", "Please correct the form below.");
			return badRequest(details.render(getFormData));
		}
		
		Product product=getFormData.get();
		//save
		product.save();
		//success message
		flash("success",String.format("Successfully added product %s", product));
//		return ok(String.format("%s saved successfully", product));
		return redirect(routes.Products.list());
	}

	//delete
	//bit of JavaScript to send a DELETE request in list.scala.html
	//add the route with verb DELETE in routes file
	public static Result delete(String ean){
		final Product product=Product.findByEan(ean);
		if(product==null){
			return notFound(String.format("Item %s not found", ean));
		}
		
		Product.remove(product);
		return redirect(routes.Products.list());
	}
	public static Result index() {
		return TODO;
	}

}
																																																