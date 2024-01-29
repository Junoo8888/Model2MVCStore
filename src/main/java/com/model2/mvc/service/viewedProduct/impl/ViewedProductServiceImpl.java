package com.model2.mvc.service.viewedProduct.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.viewedProduct.ViewedProductDao;
import com.model2.mvc.service.viewedProduct.ViewedProductService;

@Service
public class ViewedProductServiceImpl implements ViewedProductService {
	
	///Field
	private ViewedProductDao viewedProductDao; 

	///Constructor
	@Autowired
	public ViewedProductServiceImpl() {
		this.viewedProductDao = viewedProductDao;
	}
	
	///Method
	public void addViewedProduct(String userId, int prodNo) {
		viewedProductDao.addViewedProduct(userId, prodNo);
	}
	
}// end of class 
