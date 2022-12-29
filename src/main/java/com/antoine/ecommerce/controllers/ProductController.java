package com.antoine.ecommerce.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antoine.ecommerce.models.Category;
import com.antoine.ecommerce.models.Product;
import com.antoine.ecommerce.models.User;
import com.antoine.ecommerce.services.CategoryService;
import com.antoine.ecommerce.services.ProductService;
import com.antoine.ecommerce.services.UserService;

@Controller
public class ProductController {
	@Autowired
	ProductService productServ;
	@Autowired 
	UserService userServ;
	@Autowired 
	CategoryService categoryServ;
	
	// ----------- READ ALL ---------------//
	@GetMapping("/products")
	public String index(
		@ModelAttribute("userObj") User filledUser,
		HttpSession session, Model model
	) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		model.addAttribute("userName", userServ.getOneUser((Long) session.getAttribute("user_id")));
		model.addAttribute("allProducts", productServ.allProducts());
		return "/dashboard.jsp";
		
	}
	
	@GetMapping("/products/new")
	public String newProduct(
		@ModelAttribute("productObj") Product emptyProduct,
		HttpSession session
	) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		return "new.jsp";
	}
	
	@PostMapping("/products/new")
	public String processProduct(
		@Valid @ModelAttribute("productObj") Product filledProduct,
		BindingResult results
	) {
		// VALIDATIONS FAIL
		if(results.hasErrors()) {
			return "new.jsp";
		}
		@SuppressWarnings("unused")
		Product newProduct = productServ.addProduct(filledProduct);
		return "redirect:/products/browse";
//		return "redirect:/products/" + newProduct.getId();
	}
	
	@GetMapping("/products/browse")
	public String browse(
			@ModelAttribute("userObj") User filledUser,
	HttpSession session, Model model
	){
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		model.addAttribute("userName", userServ.getOneUser((Long) session.getAttribute("user_id")));
		model.addAttribute("allProducts", productServ.allProducts());
		return "/all_products.jsp";
		
	}
	
	@GetMapping("/products/{id}")
	public String oneProduct(
		@PathVariable("id") Long id,
		Model model, @ModelAttribute("userObj") User filledUser,
		HttpSession session
	) {
		model.addAttribute("userName", userServ.getOneUser((Long) session.getAttribute("user_id")));
		model.addAttribute("oneProduct", productServ.findById(id) );
		return "show.jsp";
	}
	
	// ---------- UPDATE --------------//
	@GetMapping("/products/{id}/edit")
	public String edit(
		@PathVariable("id") Long id,
		Model model, @ModelAttribute("userObj") User filledUser,
		HttpSession session
	) {
		model.addAttribute("userName", userServ.getOneUser((Long) session.getAttribute("user_id")));
		model.addAttribute("productObj", productServ.findById(id));
		return "edit.jsp";
	}
	@PutMapping("/products/{id}/edit")
	public String update(
		@Valid @ModelAttribute("productObj") Product filledProduct,
		BindingResult results
	) {
		if(results.hasErrors()) {
			return "edit.jsp";
		}
		productServ.addProduct(filledProduct);
		return "redirect:/products/browse";
	}
	// ---------- UPDATE --------------//
	
	// ---------- DELETE --------------//
	@GetMapping("/products/{id}/delete")
	public String delete(
		@PathVariable("id") Product id
	) {
		productServ.deleteProduct(id);
		return "redirect:/products/browse";
	}
	@DeleteMapping("/products/{id}")
	public String deleteProduct(
		@PathVariable("id") Product id
	) {
		productServ.deleteProduct(id);
		return "redirect:/products/browse";
	}
	// ---------- DELETE --------------//

	
	@GetMapping("/account")
	public String account() {
		return "account.jsp";
	}
	
	@GetMapping("/orders")
	public String orders() {
		return "orders.jsp";
	}
	
//	@GetMapping("/products/{id}")
//	public String showProduct(@PathVariable("id") Long id, Model model) {
//		Product product = productServ.findById(id);
//		model.addAttribute("assignedCategories", categoryServ.getAssignedProducts(product));
//		model.addAttribute("unassignedCategories", categoryServ.getUnassignedProducts(product));
//		model.addAttribute("product", product);
//		return "show_product.jsp";
//	}

	@PostMapping("/products/{id}")
	public String editProduct(@PathVariable("id") Long id, @RequestParam(value="categoryId") Long catId,  Model model) {
		Product product = productServ.findById(id);
		Category category = categoryServ.findById(catId);
		product.getCategories().add(category);
		productServ.updateProduct(product);
		model.addAttribute("assignedCategories", categoryServ.getAssignedProducts(product));
		model.addAttribute("unassignedCategories", categoryServ.getUnassignedProducts(product));
		return "redirect:/products/" + id;
	}
}
