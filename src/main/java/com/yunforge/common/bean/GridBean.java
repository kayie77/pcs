package com.yunforge.common.bean;

import java.util.ArrayList;
import java.util.List;

public class GridBean<T> {
	private Integer page;
	private Integer total;
	private Integer records;
	private Integer rowBegin;
	private Integer rowEnd;
	private int limitEnd;
	private int limitBegin;
	
	private List<T> rows = new ArrayList<T>();

	public GridBean() {

	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getRowBegin() {
		return rowBegin;
	}

	public void setRowBegin(Integer rowBegin) {
		this.rowBegin = rowBegin;
	}

	public Integer getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(Integer rowEnd) {
		this.rowEnd = rowEnd;
	}

	public int getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(int limitEnd) {
		this.limitEnd = limitEnd;
	}

	public int getLimitBegin() {
		return limitBegin;
	}

	public void setLimitBegin(int limitBegin) {
		this.limitBegin = limitBegin;
	}


}
