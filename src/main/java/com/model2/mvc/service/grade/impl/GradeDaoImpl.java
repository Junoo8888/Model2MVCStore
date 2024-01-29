package com.model2.mvc.service.grade.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Grade;
import com.model2.mvc.service.grade.GradeDao;

@Repository
public class GradeDaoImpl implements GradeDao{
	
	///Field
	private SqlSession sqlSession;
	
	///Constructor
	public GradeDaoImpl() {
		System.out.println(this.getClass() + " 생성..");
	}
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//SQL 문제
	@Override
	public Grade getMemberGrade(long totalPurchase) {
		System.out.println("getMemberGrade.DaoImpl = > totalPurchase" + totalPurchase);
		Grade grade = new Grade();
		if(totalPurchase >= 100000) {
			
			grade.setGradeName("VIP");
		}else {
			grade.setGradeName("BASIC");
		}
		
		return grade;
	}

}
