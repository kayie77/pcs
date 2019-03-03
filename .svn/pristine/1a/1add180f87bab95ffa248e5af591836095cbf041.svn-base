package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Dict;
import com.yunforge.base.model.DictVal;

public interface DictManager {

	public Dict findDictById(String id);

	public Dict findDictByDictCode(String dictCode);

	public Dict findDictByDictName(String dictName);

	public List<Dict> findDictsByDictType(String typeId);

	public Dict saveDict(Dict dict);

	public void removeDict(Dict dict);

	public DictVal findDictValById(String valId);

	public DictVal findDictValByDictAndValCode(String dictId, String valCode);

	public DictVal findDictValByDictAndValName(String dictId, String valName);

	public List<DictVal> findDictValsByDictId(String dictId);
	
	public Page<DictVal> listDictVals(String dictId,String searchField, String searchOper,
			String searchString, Pageable pageable);

	public DictVal saveDictVal(DictVal dictVal);

	public void removeDictVal(DictVal dictVal);
}