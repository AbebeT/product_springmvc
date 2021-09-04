package edu.mum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

@RequestMapping("/product")
@Controller
public class ProductController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@GetMapping
	public String getProductForm(Model model) {
		model.addAttribute("categories", categoryService.getAll());
		// System.out.println("all catorgies: " + categoryService.getAll());
		return "ProductForm";
	}

   @PostMapping
	public String saveProduct(Product product) {
		Category category = categoryService.getCategory(product.getCategory().getId());
		product.setCategory(category);
		productService.save(product);
		return "ProductDetails";
	}

    @RequestMapping("/listProducts")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.getAll());
		System.out.println("products: " + productService.getAll());
		return "ListProducts";
	}


	@RequestMapping("/productfind")
	public String findProduct() {
		return "Find";
	}

	@RequestMapping("/product-find")
	public String productDetails(@RequestParam("id") long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "SingleProduct";
	}
}
