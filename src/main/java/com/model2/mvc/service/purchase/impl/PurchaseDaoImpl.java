package com.model2.mvc.service.purchase.impl;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;

@Repository
public class PurchaseDaoImpl implements PurchaseDao{

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("PurchaseDaoImpl SqlSession Create");
		this.sqlSession = sqlSession;
	}	
	
	@Override
	public Product addPurchaseView(Product product, User user) {
		// 구현하지않음 
		return null;
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public List<Purchase>getSalesProductList(Search search) throws Exception {
		
		List<Object> list = new ArrayList<Object>();
		list.add(search);

		Map<String , Object> map = new HashMap<String, Object>();
		map.put("search", search);
		
		return sqlSession.selectList("PurchaseMapper.getSalesProductList", map );
	}
	
	public int getTotalCount(Search search) throws Exception {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("search", search);
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", map);
	}
	
	@Override
	public List<Purchase> listPurchase(Search search, String userId) throws Exception {

		Map<String , Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		
		return sqlSession.selectList("PurchaseMapper.getSalesProductList", map );
	}
	
	public int getUserPurchaseCount(Search search, String userId) throws Exception {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		
		return sqlSession.selectOne("PurchaseMapper.getUserPurchaseCount", map);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.selectOne("PurchaseMapper.updateTranCode", purchase);
	}






}// end of class 
