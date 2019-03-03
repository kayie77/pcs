package com.yunforge.collect.service;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.DataReoprtedDetail;


public interface DataReoprtedDetailManager {
	
	public DataReoprtedDetail getDataReoprtedDetail(String id);

	List<DataReoprtedDetail> getDataReoprtedDetailByDmId(String dmId);
	//Page<DataReoprtedDetail> getDataReoprtedDetailByDmId(String dmId, Map<String, Object> searchParams,Pageable pageable);
	Page<DataReoprtedDetail> receiveDataReoprtedDetailByDmId(String dmId, Map<String, Object> searchParams,Pageable pageable);
	public void saveReoprtedDetailData(String[] id,String[] data) throws Exception;
	public void convertedIndexNameByCode(List<DataReoprtedDetail> drlist,List<DataCollectIndex> dclist);

}
