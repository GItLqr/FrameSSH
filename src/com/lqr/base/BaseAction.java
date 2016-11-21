package com.lqr.base;

import java.lang.reflect.ParameterizedType;

import com.lqr.service.ClassesService;
import com.lqr.service.CourseTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	// 1、统一模型驱动
	private T t;

	public BaseAction() {
		try {
			// 得到BaseAction<T>
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			// 得到T的实际类型
			Class<?> entityClass = (Class<?>) type.getActualTypeArguments()[0];
			// 创建T对象
			t = (T) entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return t;
	}

	// 2、统一分页数据
	private int pageNum = 1;// 默认从第一页开始

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	private int pageSize = 2;// 固定每次从数据库取数据的个数

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 3、统一值栈操作
	public void push(Object o) {
		ActionContext.getContext().getValueStack().push(o);
	}

	public void set(String key, Object o) {
		ActionContext.getContext().getValueStack().set(key, o);
	}

	public void put(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	public void putSession(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	public void putApplication(String key, Object value) {
		ActionContext.getContext().getApplication().put(key, value);
	}

	// 4、统一所有的service
	
	private CourseTypeService courseTypeService;
	private ClassesService classesService;
	
	public CourseTypeService getCourseTypeService() {
		return courseTypeService;
	}

	public void setCourseTypeService(CourseTypeService courseTypeService) {
		this.courseTypeService = courseTypeService;
	}

	public ClassesService getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

}
