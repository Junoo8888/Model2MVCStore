package com.model2.mvc.service.product.impl;

import java.util.HashMap; 
import java.util.List;
import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

import com.model2.mvc.service.user.UserDao;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
	 
	//Field
	@Autowired
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//Constructor
	public ProductServiceImpl() {
		System.out.println(this.getClass() + " 생성..");
	}
	
	@Override
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
		
	}
	

	public Product getProduct(int prodNo) throws Exception {
		
		
		
		return productDao.findProduct(prodNo);
	}
	
	public void updateProduct(Product product)throws Exception{
		productDao.updateProduct(product);
	}
	
	@Override // AddPurchase 호출 시 stock만 수정하는 Method
	public void updateStockProduct(Product product) throws Exception {
		productDao.updateStockProduct(product);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		
		System.out.println("ProductServiceImpl getProductList Start...");
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
	    if (list.isEmpty()) {
	        System.out.println("Product list is empty.");
	    } else {
	        System.out.println("Product list contains items:");
	        for (Product product : list) {
	            System.out.println(product); // 또는 원하는 형태로 출력 로직을 작성
	        }
	    }
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public Map<String, Object> getProductListScroll(Search search) throws Exception {
		
		System.out.println("ProductServiceImpl getProductList Start...");
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
	    if (list.isEmpty()) {
	        System.out.println("Product list is empty.");
	    } else {
	        System.out.println("Product list contains items:");
	        for (Product product : list) {
	            System.out.println(product); // 또는 원하는 형태로 출력 로직을 작성
	        }
	    }
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}


}// end of class
