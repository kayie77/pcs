package com.yunforge.api.cms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.api.dto.InfoDto;
import com.yunforge.api.dto.RelativeDto;
import com.yunforge.cms.model.Info;
import com.yunforge.cms.service.InfoManager;
import com.yunforge.common.bean.GridBean;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class InfoApiController extends BaseController {
	final static Log log = LogFactory.getLog(InfoApiController.class);

	final static Integer STATUS_DRAFT = 0;
	final static Integer STATUS_AUDIT = 1;
	final static Integer STATUS_ACCEPTED = 2;
	final static Integer STATUS_REJECTED = 3;

	@Autowired
	private InfoManager infoManager;

	@MethodRemark(remark = "移动终端访问发布信息")
	@RequestMapping(value = "/api/cms/info/list", method = RequestMethod.GET)
	public @ResponseBody
	GridBean<InfoDto> listInfos(Integer type, Integer catalog, Integer page,
			Integer rows, String sidx, String sord) {
		Page<InfoDto> infos = null;
		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (StringUtils.isNotBlank(sidx)) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.DESC, "pubDate"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);

		infos = infoManager.findByStatusAndTypeAndCatalog(Info.STATUS_ACCEPTED,
				type, catalog, pageable);

		List<InfoDto> infoList = new ArrayList<InfoDto>();
		infoList.addAll(infos.getContent());
		Integer records = (int) infos.getTotalElements();

		Integer totalPages = infos.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<InfoDto> grid = new GridBean<InfoDto>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(infoList);
		return grid;
	}


	@MethodRemark(remark = "移动终端查看信息内容")
	@RequestMapping(value = "/api/cms/info/{id}", method = RequestMethod.GET)
	public @ResponseBody
	InfoDto getInfo(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Info info = infoManager.findInfoById(id);
		BeanCopier copier = BeanCopier.create(Info.class, InfoDto.class, false);
		InfoDto infoDto = new InfoDto();
		String body=info.getBody();
		body = body.replaceAll("(.*?)src=\"(.*?)", "$1src=\"http://192.168.40.2:8080$2");
		
		copier.copy(info, infoDto, null);
		infoDto.setBody(body);
		RelativeDto relative=new RelativeDto();
		relative.setTitle("测试其他新闻");
		relative.setUrl("http://www.csdn.net/article/2015-10-04/2825843");
		infoDto.getRelatives().add(relative);
		return infoDto;
	}
}
