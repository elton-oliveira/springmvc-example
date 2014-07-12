package br.com.fluentcode.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fluentcode.springmvc.dao.ProductDAO;

@Controller
@RequestMapping(value="/products")
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;

	// url: /products/list.html
	@RequestMapping(value="/list")
	public ModelAndView list() {
		// view
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("products", productDAO.findAll());
		return modelAndView;
	}
}
