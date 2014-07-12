package br.com.fluentcode.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fluentcode.springmvc.dto.Product;

@Controller
@RequestMapping(value="/products")
public class ProductsController {

	// url: /products/list.html
	@RequestMapping(value="/list")
	public ModelAndView list() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("Refrigerator", 5489.99));
		products.add(new Product("Stove", 3749.99));
		
		// view
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("products", products);
		return modelAndView;
	}
}
