package cz.etn.etnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.service.ProductService;



@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("product/list");
		System.out.println("Count:" + productService.getProducts().size());
		modelAndView.addObject("test", "mytest");
		modelAndView.addObject("count", productService.getProducts().size());
		modelAndView.addObject("products", productService.getProducts());
	    return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addForm() {
		ModelAndView modelAndView = new ModelAndView("product/addForm");
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addSubmit(@ModelAttribute Product product) {
		productService.saveProduct(product);
		ModelAndView modelAndView = new ModelAndView("product/detail");
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
}
