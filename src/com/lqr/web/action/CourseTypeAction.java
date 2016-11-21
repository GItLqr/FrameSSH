package com.lqr.web.action;

import org.apache.commons.lang3.StringUtils;

import com.lqr.base.BaseAction;
import com.lqr.domain.CrmCourseType;
import com.lqr.page.PageBean;

public class CourseTypeAction extends BaseAction<CrmCourseType> {

	public String findAll() {
		/*
		 * List<CrmCourseType> allCourseType = courseTypeService
		 * .findAll(crmCourseType);
		 * ActionContext.getContext().put("allCourseType", allCourseType);
		 */

		PageBean pageBean = this.getCourseTypeService().findAll(getModel(),
				this.getPageNum(), this.getPageSize());
		this.put("pageBean", pageBean);

		return "findAll";
	}

	public String addOrEditUI() {
		// 有id说明是编辑，没有id说明是添加
		if (StringUtils.isNotBlank(getModel().getCourseTypeId())) {
			CrmCourseType courseType = this.getCourseTypeService().findById(
					getModel().getCourseTypeId());
			this.push(courseType);
		}
		return "addOrEditUI";
	}

	public String addOrEdit() {
		this.getCourseTypeService().addOrEdit(getModel());
		return "addOrEdit";
	}
}
