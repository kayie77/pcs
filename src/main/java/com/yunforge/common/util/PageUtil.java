package com.yunforge.common.util;

import com.yunforge.base.model.Notice;
import com.yunforge.common.bean.GridBean;

public class PageUtil {

	//limit的开始
	//limit的结束
	//最小页码
	//最大页码
	//显示开始页数
	//显示结束页数
	//当前页
	public int rowCountEachPage = 10;
//	private PageInfo pageInfo;
	private GridBean gridBean;
	
	public GridBean getGridBean(String pageId,int pageSize,int rowCount) {
		
		rowCountEachPage = pageSize;
		int maxPage = getMaxPage(rowCount);
		int currentPage = getCurrentPage(pageId,maxPage);
		int limitBegin = getLimitBegin(currentPage,rowCount);
		int limitEnd = getLimitEnd(currentPage);
		int minPage = getMinPage(rowCount);
		int pageBegin = getPageBegin(currentPage);
		int pageEnd = getPageEnd(pageBegin,maxPage);
		String prevPage = getPrevPage(pageBegin);
		String nextPage = getNextPage(pageEnd,maxPage);
		
		
//		GridBean<Notice> grid = new GridBean<Notice>();
//
//		grid.setRecords(records);
//		grid.setTotal(totalPages);
//		grid.setPage(currPage);
//		grid.setRows(NoticeList);
		
		
//		pageInfo = new PageInfo();
//		pageInfo.setLimitBegin(limitBegin);
//		pageInfo.setLimitEnd(limitEnd);
//		pageInfo.setMaxPage(maxPage);
//		pageInfo.setMinPage(minPage);
//		pageInfo.setPageBegin(pageBegin);
//		pageInfo.setPageEnd(pageEnd);
//		pageInfo.setPrevPage(prevPage);
//		pageInfo.setNextPage(nextPage);
//		pageInfo.setCurrentPage(currentPage);

		
		gridBean = new GridBean();
		gridBean.setTotal(maxPage);
		gridBean.setPage(currentPage);
		gridBean.setRowBegin(limitBegin);
		gridBean.setRowEnd(limitBegin + rowCountEachPage);
		gridBean.setRecords(rowCount);
		gridBean.setLimitBegin(limitBegin);
		gridBean.setLimitEnd(limitEnd);
		
		return gridBean;
	}
	
	private String getNextPage(int pageEnd,int maxPage) {
		if(pageEnd >= maxPage) {
			return null;
		}
		return (pageEnd+1) + "";
	}
	
	private String getPrevPage(int pageBegin) {
		if(pageBegin <= 1) {
			return null;
		}
		return (pageBegin-1) + "";
	}
	
	private int getPageBegin(int pageId) {
		if(rowCountEachPage == 0) {
			return 1;
		}
		if(pageId % rowCountEachPage == 0) {
			return ((pageId-1) / rowCountEachPage) * rowCountEachPage + 1;
		} else {
			return (pageId / rowCountEachPage) * rowCountEachPage + 1;
		}
	}
	
	private int getPageEnd(int pageBegin,int maxPage) {
		if(pageBegin + (rowCountEachPage-1) > maxPage) {
			return maxPage;
		} else {
			return (pageBegin + (rowCountEachPage-1));
		}
	}
	
	private int getMinPage(int rowCount) {
		if(rowCount > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private int getLimitEnd(int limitBegin) {
//		return limitBegin + rowCountEachPage - 1;
		return rowCountEachPage;
	}
	
	public static void main(String[] args) {
		for(int i = 1;i < 123;i++) {
			System.out.println("insert into t_article values(" +i+ "," +i+ "," +i+ ",now(),now()," +i+ "," +i+ "," +1+ "," +i+ ");");
		}
	}
	
	private int getLimitBegin(int currentPage,int rowCount) {
		if(rowCount <= 1) {
			return 0;
		} else {
			return (currentPage-1)*rowCountEachPage;
		}
	}
	
	private int getMaxPage(int rowCount) {
		if(rowCount < 1) {
			return 0;
		}
		if(rowCountEachPage == 0) {
			return 1;
		}
		if(rowCount % rowCountEachPage == 0) {
			if(rowCount/rowCountEachPage == 0) {
				return 1;
			} else {
				return rowCount/rowCountEachPage;
			}
		} else {
			return rowCount/rowCountEachPage + 1;
		}
	}
	
	private int getCurrentPage(String pageId,int maxPage) {
		try {
			int pId = Integer.parseInt(pageId);
			if(pId < 1) {
				return 1;
			}
			if(pId > maxPage) {
				return maxPage;
			}
			return pId;
		} catch(Exception e) {
			return 1;
		}
	}
	
	public int getPageSize() {
		return rowCountEachPage;
	}

	public PageUtil() {}
//	public PageInfo getPageInfo() {
//		return pageInfo;
//	}

//	public GridBean getGridBean() {
//		return gridBean;
//	}

//	public void setGridBean(GridBean gridBean) {
//		this.gridBean = gridBean;
//	}
	
}
