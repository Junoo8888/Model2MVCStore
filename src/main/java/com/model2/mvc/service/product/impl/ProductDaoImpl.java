package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
		System.out.println(this.getClass() + " 생성..");
	}
	
	@Override
	public void addProduct(Product product) throws Exception {
		sqlSession.selectOne("ProductMapper.addProduct", product);
		
	}

	@Override
	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	@Override // AddPurchase 호출 시 stock만 수정하는 Method
	public void updateStockProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateStockProduct", product);
		
	}

	@Override
	public List<Product> getProductList(Search search) throws Exception {
		System.out.println("Product Dao Impl getProductList Start .... ");
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}
	
	@Override
	public List<Product> getProductListScroll(Search search) throws Exception {
		System.out.println("Product Dao Impl getProductList Start .... ");
		return sqlSession.selectList("ProductMapper.getProductListScroll", search);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}


	
	


}
