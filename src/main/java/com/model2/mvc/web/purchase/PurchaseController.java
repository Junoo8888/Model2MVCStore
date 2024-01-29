package com.model2.mvc.web.purchase;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

	///Filed
	private PurchaseService purchaseService;
	private ProductService productService;
	private UserService userService;
	///Constructor
	@Autowired
	public PurchaseController(PurchaseService purchaseService, ProductService productService, UserService userService) {
		this.purchaseService = purchaseService;
		this.productService = productService;
		this.userService = userService;
	}

	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value="addPurchaseView", method = RequestMethod.GET)
	public ModelAndView addPurchaseView(@RequestParam("prodNo") int prodNo, HttpSession session ) throws Exception {
		System.out.println("addPurchaseView.do Start ....  ");
		
		User user=  (User)session.getAttribute("user");
		
		Product product = productService.getProduct(prodNo);
		Product originProduct = productService.getProduct(prodNo);
		//할인된 금액 보여주기 넣어보자 
		product=purchaseService.addPurchaseView(product, user);
		
		

		System.out.println("originProduct = > " + originProduct);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.addObject("originProduct", originProduct);
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		
		System.out.println("addPurchaseView.do end ....  ");
		return modelAndView;
	}
	
	@RequestMapping(value="addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchase(@ModelAttribute("purchase")Purchase purchase, @RequestParam("buyerId") String userId, @RequestParam("prodNo") int prodNo ) throws Exception {
		System.out.println("addPurchase.do Start ....  ");
		System.out.println("purchase ===========================================> " + purchase);

		
		purchase.setBuyer(userService.getUser(userId));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		
		purchaseService.addPurchase(purchase);
		
		long finalPurchase = (long)(purchase.getPurchasePrice()*0.9);
		
		purchase.setPurchasePrice(finalPurchase);
		

		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		
		System.out.println("addPurchase.do end ....  ");
		return modelAndView;
	}
	
	@RequestMapping(value="getSalesProductList", method = RequestMethod.GET)
	public ModelAndView getSalesProductList(@ModelAttribute("search") Search search,  HttpServletRequest request, HttpSession session ) throws Exception {
		System.out.println("getSalesProductList Start ....  ");
		
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getSalesProductList(search);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		modelAndView.setViewName("/purchase/salesProductList.jsp");
		
		System.out.println("map.get(list) = > " + map.get("list"));
		
		
		System.out.println("getSalesProductList end ....  ");
		return modelAndView;
	}
	
	@RequestMapping(value = "listPurchase", method = RequestMethod.GET)
	public ModelAndView listPurchase(@ModelAttribute("search") Search search, HttpSession session) throws Exception {
		
		System.out.println("listPurchase Start ....  ");
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		User user = (User)session.getAttribute("user");
		System.out.println("list Purchase user - > " + user);
		
		Map<String, Object> map = purchaseService.listPurchase(search, user.getUserId());
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		
		System.out.println("listPurchase End ....  ");
		return modelAndView;
	}

	@RequestMapping(value="getPurchase", method = RequestMethod.GET)
	public ModelAndView getPurchase(@RequestParam("tranNo")int tranNo) throws Exception {
		
		System.out.println("getPurchase.do Start ....  ");
	
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		System.out.println("get Purchase. purchase ======>>>>>>>>>>>>>>>>>>>>>>>>>>>" + purchase);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/getPurchase.jsp");
		
		System.out.println("getPurchase.do End ....  ");
		return modelAndView;
	}
	
	@RequestMapping(value="updatePurchaseView", method = RequestMethod.GET)
	public ModelAndView updatePurchaseView(@RequestParam("tranNo")int tranNo) throws Exception {
		System.out.println("updatePurchaseView.do Start ....  ");

		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/updatePurchaseView.jsp");
		
		System.out.println("updatePurchaseView.do end ....  ");

		return modelAndView;
	}
	
	@RequestMapping(value="updatePurchase", method = RequestMethod.POST)
	public ModelAndView updatePurchase(@ModelAttribute("purchase")Purchase purchase) throws Exception {
		
		System.out.println("updatePurchase.do Start ....  ");

		purchaseService.updatePurchase(purchase);
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/getPurchase.jsp");

		
		System.out.println("updatePurchase.do End ....  ");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "updateTranCode", method = RequestMethod.GET)
	public ModelAndView updateTranCode(@RequestParam("tranNo")int tranNo, @RequestParam("tranCode") String tranCode) throws Exception {
	
		// 작업 멈춤 Ajax로 진행할 예정 
		
		System.out.println("updateTranCode Start ...  ");
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode(tranCode);
		
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(purchase);
		modelAndView.setViewName("/purchase/listPurchase");
		
		return modelAndView;
	}
	
}// end of class 
