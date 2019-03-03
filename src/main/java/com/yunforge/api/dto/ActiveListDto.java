package com.yunforge.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @ClassName: ActiveList
 * @Description: 动态实体列表
 * @author Oliver Wen
 * @date 2015年10月5日 上午12:53:50
 * 
 */
@JsonRootName("activeList")
public class ActiveListDto implements Serializable {
	private static final long serialVersionUID = -115601060752162852L;
	public final static int CATALOG_LASTEST = 1;// 最新
	public final static int CATALOG_ATME = 2;// @我
	public final static int CATALOG_COMMENT = 3;// 评论
	public final static int CATALOG_MYSELF = 4;// 我自己

	private int pageSize;

	private int activeCount;

	private List<ActiveDto> activelist = new ArrayList<ActiveDto>();

	private ResultDto result;

	public int getPageSize() {
		return pageSize;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public List<ActiveDto> getActivelist() {
		return activelist;
	}

	public List<ActiveDto> getRows() {
		return activelist;
	}

	public ResultDto getResult() {
		return result;
	}

	public void setResult(ResultDto result) {
		this.result = result;
	}
}
