package com.model2.mvc.service.viewedProduct.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.viewedProduct.ViewedProductDao;

@Repository
public class ViewedProductDaoImpl implements ViewedProductDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	///Constructor
	
	///method
	@Override
	public void addViewedProduct(String userId, int prodNo) {
		sqlSession.insert("");
		
	}
	

}// end of class 
