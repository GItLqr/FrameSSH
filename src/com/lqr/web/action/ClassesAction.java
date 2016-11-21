package com.lqr.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.lqr.base.BaseAction;
import com.lqr.domain.CrmClasses;
import com.lqr.utils.MyStringUtils;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ClassesAction extends BaseAction<CrmClasses> {

	public String findAll() {
		List<CrmClasses> allClasses = this.getClassesService().findAll();
		this.set("allClasses", allClasses);
		return "findAll";
	}

	public String uploadUI() {
		CrmClasses classes = this.getClassesService().findById(
				getModel().getClassId());
		push(classes);
		return "uploadUI";
	}

	private File schedule; // 文件内容
	private String scheduleFileName; // 文件名
	private String scheduleContentType; // 文件类型

	public void setSchedule(File schedule) {
		this.schedule = schedule;
	}

	public void setScheduleFileName(String scheduleFileName) {
		this.scheduleFileName = scheduleFileName;
	}

	public void setScheduleContentType(String scheduleContentType) {
		this.scheduleContentType = scheduleContentType;
	}

	@InputConfig(resultName = "uploadInput")
	// 如果上传出错，result的name为uploadInput
	public String upload() {
		// 得到文件的上传目录
		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/WEB-INF/upload");

		// 随机产生保存到磁盘的文件名（避免文件同名冲突）
		String saveFileName = MyStringUtils.getUUID();

		try {
			// 保存上传的文件
			FileUtils.copyFile(schedule, new File(savePath, saveFileName));

			// 更新数据库信息
			CrmClasses classes = getModel();
			classes.setUploadTime(new Date());
			classes.setUploadFilename(scheduleFileName);
			classes.setUploadPath(savePath);
			this.getClassesService().upload(classes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "upload";
	}
}
