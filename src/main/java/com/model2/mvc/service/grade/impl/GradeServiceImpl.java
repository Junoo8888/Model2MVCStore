package com.model2.mvc.service.grade.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Grade;
import com.model2.mvc.service.grade.GradeDao;
import com.model2.mvc.service.grade.GradeService;

@Service
public class GradeServiceImpl implements GradeService{

	///Field
	private GradeDao gradeDao;
	
	///Constructor
	@Autowired
	public GradeServiceImpl(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}
	
	///Method
	@Override
	public Grade getMemberGrade(long totalPurchase) {
		return gradeDao.getMemberGrade(totalPurchase);
	}

}// end of class 
