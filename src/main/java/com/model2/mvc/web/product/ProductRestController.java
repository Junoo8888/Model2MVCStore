package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductRestController() {
		System.out.println(this.getClass());
	}
	
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value = "json/getProduct/{prodNo}", method=RequestMethod.GET)
	public Product getProduct(@PathVariable int prodNo)throws Exception{
		
		System.out.println("/product/json/getProduct... Start");
		
		
		
		System.out.println("/product/json/getProduct... End");
		
		return productService.getProduct(prodNo);
	}
	


	
	@RequestMapping(value="json/listProductScroll", method = RequestMethod.GET)
	public Map<String, Object> listProductScroll(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {
		
		System.out.println("listProductScroll Strat....");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> searchMap=productService.getProductListScroll(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)searchMap.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", searchMap.get("list"));
		map.put("resultPage", resultPage);
		map.put("search", search);
	
		return map;

	}
	
	@RequestMapping(value="json/listProduct", method = RequestMethod.GET)
	public Map<String, Object> listProduct(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {
		
		System.out.println("listProductScroll Strat....");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> searchMap=productService.getProductListScroll(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)searchMap.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", searchMap.get("list"));
		map.put("resultPage", resultPage);
		map.put("search", search);
	
		return map;

	}
}// end of class 
