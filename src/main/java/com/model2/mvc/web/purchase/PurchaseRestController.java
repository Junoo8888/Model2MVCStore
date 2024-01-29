package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {

	private PurchaseService purchaseService;
	private ProductService productService;
	private UserService userService;
	
	///Constructor
	@Autowired
	public PurchaseRestController(PurchaseService purchaseService, ProductService productService, UserService userService) {
		this.purchaseService = purchaseService;
		this.productService = productService;
		this.userService = userService;
	}
	
	@RequestMapping(value="json/updateTranCode", method = RequestMethod.POST)
	public Purchase updateTranCode(@RequestBody Purchase purchase)throws Exception {
		
		System.out.println("Rest: updateTranCode start ... ");
		
		System.out.println(purchase);
		
		purchaseService.updateTranCode(purchase);
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		    
		
		return purchase;
	}

}// end of class 
