package cz.etn.etnshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView("product/detail");
		modelAndView.addObject("product", productService.getProduct(id));
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editForm(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView("product/editForm");
		modelAndView.addObject("product", productService.getProduct(id));
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editSubmit(@PathVariable int id, @ModelAttribute Product newData){
		ModelAndView modelAndView = new ModelAndView("product/detail");
		Product product = productService.getProduct(id);
		product.setName(newData.getName());
		product.setSerialNumber(newData.getSerialNumber());
		productService.updateProduct(product);
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(){
		return new ModelAndView("product/searchForm");
	}
	
	@RequestMapping(value="/search", params={"query"})
	public ModelAndView search(@RequestParam("query") String query){
		ModelAndView modelAndView;
		if(query==null||"".equals(query)){
			modelAndView = new ModelAndView("product/searchForm");
		}else{
			modelAndView = new ModelAndView("product/list");
			modelAndView.addObject("test", "mytest");
			List<Product> products = productService.findProducts(query);
			modelAndView.addObject("count", products.size());
			modelAndView.addObject("products", products);
		}
		return modelAndView;
	}
}
