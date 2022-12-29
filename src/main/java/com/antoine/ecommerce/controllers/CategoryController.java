package com.antoine.ecommerce.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antoine.ecommerce.models.Category;
import com.antoine.ecommerce.models.Product;
import com.antoine.ecommerce.models.User;
import com.antoine.ecommerce.services.CategoryService;
import com.antoine.ecommerce.services.ProductService;
import com.antoine.ecommerce.services.UserService;

@Controller
public class CategoryController {
	@Autowired
	ProductService productServ;
	@Autowired 
	UserService userServ;
	@Autowired 
	CategoryService categoryServ;
	
	@GetMapping("/products/categories")
	public String categories(
			@ModelAttribute("userObj") User filledUser,
	HttpSession session, Model model
	){
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		model.addAttribute("userName", userServ.getOneUser((Long) session.getAttribute("user_id")));
		model.addAttribute("allCategories", categoryServ.allCategories());
		return "/categories.jsp";
		
	}
	
	
	
//	@GetMapping("/products/categories")
//	public String categories(
//		@ModelAttribute("categoryObj") Category filledCategory, Long id,
//		HttpSession session, Model model
//	) {
//		if(session.getAttribute("user_id") == null) {
//			return "redirect:/";
//		}
//		model.addAttribute("userName", userServ.getOneUser((Long) session.getAttribute("user_id")));
//		model.addAttribute("allCategories", categoryServ.allCategories());
//		model.addAttribute("category", categoryServ.findById(id));
//		return "/categories.jsp";
//	
//	}
//	
//	@GetMapping("/products/categories/{id}")
//	public String showCategory(@PathVariable("id") Long id, Model model) {
//		Category category = categoryServ.findById(id);
//		model.addAttribute("assignedProducts", productServ.getAssignedCategories(category));
//		model.addAttribute("unassignedProducts", productServ.getUnassignedCategories(category));
//		model.addAttribute("category", categoryServ.findById(id));
//		return "category.jsp";
//	}
	
//	@GetMapping("products/categories/new")
//	public String newCategory(@ModelAttribute("category") Category category, Long id, Model model) {
//		model.addAttribute("category", categoryServ.findById(id));
//		return "categories.jsp";
//	}
//	
//	@PostMapping("products/categories/new")
//	public String addNewCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Long id, Model model) {
//		if(result.hasErrors()) {
//			return "categories.jsp";
//		}else {
//			categoryServ.addCategory(category);
//			model.addAttribute("category", categoryServ.findById(id));
//			return "redirect:/";
//		}
//	}
}