package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public interface PurchaseDao {
	
	public Product addPurchaseView(Product product, User user);
	
	public void addPurchase(Purchase purchase)throws Exception;
	
	public Purchase getPurchase(int tranNo)throws Exception;
	
	public void updatePurchase(Purchase purchase)throws Exception;

	public List<Purchase> getSalesProductList(Search search) throws Exception;
	
	public int getTotalCount(Search search) throws Exception ;
	
	public List<Purchase> listPurchase(Search search, String userId) throws Exception;
	
	public int getUserPurchaseCount(Search search, String userId) throws Exception ;
	
	public void updateTranCode(Purchase purchaseVO) throws Exception;

}// end of class 
