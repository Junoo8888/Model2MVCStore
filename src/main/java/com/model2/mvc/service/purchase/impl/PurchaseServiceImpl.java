package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Grade;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.grade.GradeDao;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	// Field
	private PurchaseDao purchaseDao;
	private ProductDao productDao;
	private UserDao userDao;
	private GradeDao gradeDao;

	// Constructor
	public PurchaseServiceImpl() {

	}

	@Autowired
	public PurchaseServiceImpl(ProductDao productDao, UserDao userDao, PurchaseDao purchaseDao, GradeDao gradeDao) {

		System.out.println(this.getClass() + " 생성.. 4개");

		this.purchaseDao = purchaseDao;
		this.productDao = productDao;
		this.userDao = userDao;
		this.gradeDao = gradeDao;
	}

	// Method
	@Override
	public Product addPurchaseView(Product product, User user) {
		Grade grade = gradeDao.getMemberGrade(user.getTotalPurchase());
		
		if(grade.getGradeName()=="VIP") {
			long finalPrice = (long)(product.getPrice()*0.9);
			product.setPrice(finalPrice);
		}
		
		
		return product;
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {

		Product product = productDao.findProduct(purchase.getPurchaseProd().getProdNo());
		User user = userDao.getUser(purchase.getBuyer().getUserId());
		Grade grade = gradeDao.getMemberGrade(user.getTotalPurchase());
		
		// product 수량 보다 같거나 작을 경우 실행
		if (product.getStock() >= purchase.getQuantity()) {

			product.setStock(product.getStock() - purchase.getQuantity());

			// product 수량 수정
			productDao.updateStockProduct(product);

			// 구매 상품 추가
			purchaseDao.addPurchase(purchase);

			// 유저 누적 금액 추가
			long totalPurchase = (product.getPrice() * purchase.getQuantity());
			userDao.updateUserTotalPurchase(purchase.getBuyer().getUserId(), totalPurchase);
			
			//회원 등급 가져오기 
			if(grade.getGradeName()=="VIP") {
				long finalPrice = (long)(product.getPrice()*0.9);
				product.setPrice(finalPrice);
			}

		}

	}// end of add Purchase

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		System.out.println("PurcahseServiceImpl ==> getPurchase-->>>>>>>>");
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		System.out.println("PurcahseServiceImpl ==> updatePurchase-->>>>>>>>");
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public Map<String, Object> getSalesProductList(Search search) throws Exception {
		int totalCount = purchaseDao.getTotalCount(search);
		List<Purchase> list = purchaseDao.getSalesProductList(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> listPurchase(Search search, String userId) throws Exception {
		int totalCount = purchaseDao.getUserPurchaseCount(search, userId);
		List<Purchase> list = purchaseDao.getSalesProductList(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}





}// end of class
