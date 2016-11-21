package com.lqr.page;

import java.util.List;

/**
 * @author CSDN_LQR
 * @工程 FrameSSH 
 * @包名 com.lqr.page
 * @TODO 分页类
 */
public class PageBean {

	// 必选项
	public int pageNum;
	public int pageSize;
	public int totalRecord;

	// 计算项
	public int startIndex;
	public int totalPage;

	// 数据
	public List data;

	// 显示进行条(上一页、下一页之间的所有数字)
	public int start;
	public int end;

	public PageBean(int pageNum, int pageSize, int totalRecord) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;

		// 根据当前页数和总记录数计算出开始索引和总页面数
		this.startIndex = (pageNum - 1) * pageSize;
		this.totalPage = (totalRecord + pageSize - 1) / pageSize;

		// 默认是1到10
		this.start = 1;
		this.end = 10;

		if (this.totalPage < 10) {// this.totalPage = 4
			this.end = this.totalPage;
		} else {// this.totalPage = 22
			// 前5后4
			this.start = this.pageNum - 5;
			this.end = this.pageNum + 4;

			if (this.start < 1) {
				this.start = 1;
				this.end = 10;
			} else if (this.end > this.totalPage) {
				this.end = this.totalPage;
				this.start = this.totalPage - 9;
			}
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

}
