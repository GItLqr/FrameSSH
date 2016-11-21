package com.lqr.service;

import java.util.List;

import com.lqr.domain.CrmCourseType;
import com.lqr.page.PageBean;

public interface CourseTypeService {

	List<CrmCourseType> findAll();

	List<CrmCourseType> findAll(CrmCourseType crmCourseType);

	PageBean findAll(CrmCourseType crmCourseType, int pageNum, int pageSize);

	CrmCourseType findById(String courseTypeId);

	void addOrEdit(CrmCourseType courseType);

}
