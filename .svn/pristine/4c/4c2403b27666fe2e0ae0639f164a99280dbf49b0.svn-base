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
import com.yunforge.collect.dao.DataCollectCategoryDao;
import com.yunforge.collect.model.DataCollectCategory;
import com.yunforge.collect.service.DataCollectCategoryManager;

@Service("DataCollectCategoryManager")
public class DataCollectCategoryManagerImpl implements DataCollectCategoryManager{
	
	final static Log log = LogFactory.getLog(DataCollectCategoryManagerImpl.class);
	
	@Autowired
	private DataCollectCategoryDao dataCollectCategoryDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TreeNode> getDataCollectCategoryTreeNode() {
		// TODO Auto-generated method stub
		
		List<DataCollectCategory> apcList = null;
		apcList = getDataCollectCategory();
		return toTreeNode(apcList);
	}
	
	private List<TreeNode> toTreeNode(List<DataCollectCategory> apcList){
		List<TreeNode> cList = new ArrayList();
		Iterator<DataCollectCategory> iterator = apcList.iterator();
		while (iterator.hasNext()) {
			DataCollectCategory dcc = iterator.next();
			TreeNode ctgTreeNode = null;
			ctgTreeNode = new TreeNode(dcc.getId(), dcc.getName());
			cList.add(ctgTreeNode);
		}
		return cList;
	}
	
	@Override
	public List<DataCollectCategory> getDataCollectCategory() {
			return dataCollectCategoryDao.findDataCollectCategoryByOrderByCode();
	}
	
}
