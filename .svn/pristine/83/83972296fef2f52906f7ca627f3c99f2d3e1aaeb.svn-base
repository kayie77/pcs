package com.yunforge.support.sql;

import java.util.Iterator;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;


public class PageableParser {

	private Pageable pageable;
	public PageableParser(Pageable pageable) {
		this.pageable = pageable;
	}
	
	public int getTotalPage(int totalSize) {
		int page = (int) Math.ceil((double)totalSize/pageable.getPageSize());
		return page == 0 ? 1 : page;
	}
	
	public int getPageStart() {
		return (pageable.getPageNumber())*pageable.getPageSize();
	}
	
	public int getPageNumber() {
		return pageable.getPageNumber();
	}
	
	public int getPageSize() {
		return pageable.getPageSize();
	}
	
	public String getOrderSql(Boolean full) {
		Sort sort = pageable.getSort();
		if(sort == null){
			return "";
		}
		
		StringBuilder orderbBuilder = new StringBuilder();
		if (full) {
			orderbBuilder.append(" order by ");
		}else {
			orderbBuilder.append(",");
		}
		Iterator<Order> iterator = sort.iterator();
		while (true) {
			Order order =  iterator.next();
			orderbBuilder.append(order.getProperty());
			orderbBuilder.append(" ");
			orderbBuilder.append(order.getDirection().name());
			if (!iterator.hasNext()) {
				break;
			}
			orderbBuilder.append(",");
		}
		
		return orderbBuilder.toString();
	}
}
