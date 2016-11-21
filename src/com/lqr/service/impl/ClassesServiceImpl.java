package com.lqr.service.impl;

import java.util.List;

import com.lqr.dao.ClassesDao;
import com.lqr.domain.CrmClasses;
import com.lqr.service.ClassesService;

public class ClassesServiceImpl implements ClassesService {

	private ClassesDao classesDao;

	public void setClassesDao(ClassesDao classesDao) {
		this.classesDao = classesDao;
	}

	@Override
	public List<CrmClasses> findAll() {
		return classesDao.findAll();
	}

	@Override
	public CrmClasses findById(String id) {
		return classesDao.findById(id);
	}

	@Override
	public void upload(CrmClasses classes) {
		// 局部更新
		// 1、先查询到更新的班级信息
		CrmClasses dbClasses = classesDao.findById(classes.getClassId());
		// 2、设置要更新的信息（持久态会比较session一级缓存与快照，若不同则自动更新）
		dbClasses.setUploadTime(classes.getUploadTime());
		dbClasses.setUploadFilename(classes.getUploadFilename());
		dbClasses.setUploadPath(classes.getUploadPath());
	}

}
