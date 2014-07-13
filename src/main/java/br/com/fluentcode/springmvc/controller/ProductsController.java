package br.com.fluentcode.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fluentcode.springmvc.dao.ProductDAO;
import br.com.fluentcode.springmvc.entity.Product;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	// url: /products/list.html
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		// view
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("products", productDAO.findAll());
		return modelAndView;
	}

	// url: /products/detail/1.html
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable("id") Integer id) {
		// view
		ModelAndView modelAndView = new ModelAndView("product/detail");
		modelAndView.addObject("product", productDAO.findById(id));
		return modelAndView;
	}
	
	// url: /products/create.html
	@RequestMapping(value = "/create", method=RequestMethod.GET)
	public String create() {
		// view
		return "product/create";
	}

	// url: /products/save.html
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Product product) {
		productDAO.insert(product);
		// invokes the list method
		return "redirect:/products/list.html";
	}

}
