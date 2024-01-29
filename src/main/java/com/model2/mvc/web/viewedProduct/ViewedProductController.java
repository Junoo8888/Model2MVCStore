package com.model2.mvc.web.viewedProduct;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.viewedProduct.ViewedProductService;

//@Controller
public class ViewedProductController {
	
	///Field
	private ViewedProductService viewedProductService;
	
	///Constructor
	@Autowired
	public ViewedProductController(ViewedProductService viewedProductService) {
		this.viewedProductService = viewedProductService;
	}
	
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value = "listViewedProduct", method = RequestMethod.GET)
	public String listViewedProduct(@ModelAttribute("search") Search search, Model model, HttpServletRequest request, HttpSession session) {
		
		System.out.println("listProductScroll Strat....");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		User user = (User)session.getAttribute("user");
		
		
		
		/*model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
*/
		
		return null;
	}
	
}// end of class  
