package com.lqr.service;

import java.util.List;

import com.lqr.domain.CrmClasses;

public interface ClassesService {

	List<CrmClasses> findAll();

	CrmClasses findById(String id);

	void upload(CrmClasses classes);

}
