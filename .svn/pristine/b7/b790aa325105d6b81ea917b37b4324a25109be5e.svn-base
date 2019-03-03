package com.yunforge.collect.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunforge.collect.dto.TreeNode;
import com.yunforge.collect.dao.AgrProCategoryDao;
import com.yunforge.collect.model.GropInfo;
import com.yunforge.collect.service.AgrProCategoryManager;

@Service("AgrProCategoryManager")
public class AgrProCategoryManagerImpl implements AgrProCategoryManager{
	
	final static Log log = LogFactory.getLog(AgrProCategoryManagerImpl.class);
	
	@Autowired
	private AgrProCategoryDao agrProCategoryDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TreeNode> getChildrenAgrProCategoryTreeNode() {
		// TODO Auto-generated method stub
		
		List<GropInfo> apcList = null;
		apcList = getChildrenAgrProCategory();
		return toTreeNode(apcList);
	}
	
	private List<TreeNode> toTreeNode(List<GropInfo> apcList){
		List<TreeNode> cList = new ArrayList();
		Iterator<GropInfo> iterator = apcList.iterator();
		while (iterator.hasNext()) {
			GropInfo apr = iterator.next();
			TreeNode ctgTreeNode = null;
			ctgTreeNode = new TreeNode(apr.getId(), apr.getName());
			cList.add(ctgTreeNode);
		}
		return cList;
	}
	
	@Override
	public List<GropInfo> getChildrenAgrProCategory() {
			return agrProCategoryDao.findGropInfoByOrderByCode();
	}
	
	@Override
	public List<GropInfo> getAllAgrProCategory(){
		return agrProCategoryDao.findAll();
		
	}
	
}
