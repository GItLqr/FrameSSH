package com.lqr.domain;

import java.util.HashSet;
import java.util.Set;

public class CrmCourseType {

	/*
		 CREATE TABLE `crm_course_type` (
		  `courseTypeId` VARCHAR(255) NOT NULL PRIMARY KEY,
		  `courseCost` DOUBLE DEFAULT NULL,
		  `total` INT(11) DEFAULT NULL,
		  `courseName` VARCHAR(500) DEFAULT NULL,
		  `remark` VARCHAR(5000) DEFAULT NULL
		);
	 */
	
	private String courseTypeId;
	private Double  courseCost;
	private Integer total;
	private String courseName;
	private String remark;
	private String totalStart;
	private String totalEnd;
	private String courseCostStart;
	private String courseCostEnd;
	
	//一对多：一个课程类型对应多个班级
	private Set<CrmClasses> classesSet = new HashSet<CrmClasses>();

	public String getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public Double getCourseCost() {
		return courseCost;
	}

	public void setCourseCost(Double courseCost) {
		this.courseCost = courseCost;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<CrmClasses> getClassesSet() {
		return classesSet;
	}

	public void setClassesSet(Set<CrmClasses> classesSet) {
		this.classesSet = classesSet;
	}

	public String getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(String totalStart) {
		this.totalStart = totalStart;
	}

	public String getTotalEnd() {
		return totalEnd;
	}

	public void setTotalEnd(String totalEnd) {
		this.totalEnd = totalEnd;
	}

	public String getCourseCostStart() {
		return courseCostStart;
	}

	public void setCourseCostStart(String courseCostStart) {
		this.courseCostStart = courseCostStart;
	}

	public String getCourseCostEnd() {
		return courseCostEnd;
	}

	public void setCourseCostEnd(String courseCostEnd) {
		this.courseCostEnd = courseCostEnd;
	}

	

}
