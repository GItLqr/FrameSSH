package com.lqr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lqr.dao.CourseTypeDao;
import com.lqr.domain.CrmCourseType;
import com.lqr.page.PageBean;
import com.lqr.service.CourseTypeService;

public class CourseTypeServiceImpl implements CourseTypeService {

	private CourseTypeDao courseTypeDao;

	public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
		this.courseTypeDao = courseTypeDao;
	}

	@Override
	public List<CrmCourseType> findAll() {
		return courseTypeDao.findAll();
	}

	@Override
	public List<CrmCourseType> findAll(CrmCourseType crmCourseType) {

		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(crmCourseType.getCourseName())) {
			sb.append(" and courseName like ? ");
			list.add("%" + crmCourseType.getCourseName() + "%");
		}
		if (StringUtils.isNotBlank(crmCourseType.getRemark())) {
			sb.append(" and remark like ? ");
			list.add("%" + crmCourseType.getRemark() + "%");
		}

		if (StringUtils.isNotBlank(crmCourseType.getTotalStart())) {
			sb.append(" and total>=? ");
			list.add(Integer.parseInt(crmCourseType.getTotalStart()));
		}
		if (StringUtils.isNotBlank(crmCourseType.getTotalEnd())) {
			sb.append(" and total<=? ");
			list.add(Integer.parseInt(crmCourseType.getTotalEnd()));
		}

		if (StringUtils.isNotBlank(crmCourseType.getCourseCostStart())) {
			sb.append(" and courseCost>=? ");
			list.add(Double.parseDouble(crmCourseType.getCourseCostStart()));
		}
		if (StringUtils.isNotBlank(crmCourseType.getCourseCostEnd())) {
			sb.append(" and courseCost<=? ");
			list.add(Double.parseDouble(crmCourseType.getCourseCostEnd()));
		}

		String condition = sb.toString();
		Object[] params = list.toArray();

		return courseTypeDao.findAll(condition, params);
	}

	@Override
	public CrmCourseType findById(String courseTypeId) {
		return courseTypeDao.findById(courseTypeId);
	}

	@Override
	public void addOrEdit(CrmCourseType courseType) {
		courseTypeDao.saveOrUpdate(courseType);
	}

	/*
	 * 条件+分页
	 */
	@Override
	public PageBean findAll(CrmCourseType crmCourseType, int pageNum,
			int pageSize) {

		// 1、设置查询条件
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(crmCourseType.getCourseName())) {
			sb.append(" and courseName like ? ");
			list.add("%" + crmCourseType.getCourseName() + "%");
		}
		if (StringUtils.isNotBlank(crmCourseType.getRemark())) {
			sb.append(" and remark like ? ");
			list.add("%" + crmCourseType.getRemark() + "%");
		}

		if (StringUtils.isNotBlank(crmCourseType.getTotalStart())) {
			sb.append(" and total>=? ");
			list.add(Integer.parseInt(crmCourseType.getTotalStart()));
		}
		if (StringUtils.isNotBlank(crmCourseType.getTotalEnd())) {
			sb.append(" and total<=? ");
			list.add(Integer.parseInt(crmCourseType.getTotalEnd()));
		}

		if (StringUtils.isNotBlank(crmCourseType.getCourseCostStart())) {
			sb.append(" and courseCost>=? ");
			list.add(Double.parseDouble(crmCourseType.getCourseCostStart()));
		}
		if (StringUtils.isNotBlank(crmCourseType.getCourseCostEnd())) {
			sb.append(" and courseCost<=? ");
			list.add(Double.parseDouble(crmCourseType.getCourseCostEnd()));
		}

		String condition = sb.toString();
		Object[] params = list.toArray();

		// 2、设置分页
		// 2.1 总记录数
		int totalRecord = courseTypeDao.getTotalRecord(condition, params);
		// 2.2 创建对象
		PageBean pageBean = new PageBean(pageNum, pageSize, totalRecord);
		// 2.3 分页数据
		List<CrmCourseType> data = courseTypeDao.findAll(condition, params,
				pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);

		return pageBean;
	}

}
