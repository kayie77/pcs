package com.yunforge.setup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Org;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.ResourceManager;
import com.yunforge.base.service.RoleManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class SetupController extends BaseController {
	final static Log log = LogFactory.getLog(SetupController.class);

	@Autowired
	private ResourceManager resourceManager;

	@Autowired
	private OrgManager orgManager;

	@Autowired
	private UserManager userManager;

	@Autowired
	private RoleManager roleManager;

	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		return "/setup/index";
	}

	@RequestMapping(value = "/setup/org")
	public @ResponseBody
	JSONObject installOrgData(HttpServletRequest request, ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		try {
			List<Org> deptList = new ArrayList<Org>();

			Org org = new Org("10000", "农业厅机关处室", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西壮族自治区政府农业主管部门", 1,
					"http://www.gxny.gov.cn", Boolean.TRUE);

			Org dept = new Org("10001", "农业厅办公室", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅办公室", 2,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10002", "农业厅人事处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅办公室", 3,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10003", "农业厅政策法规处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅政策法规处", 4,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10004", "农业厅农村经济体制与经营管理处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅农村经济体制与经营管理处", 5,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10005", "农业厅市场与经济信息处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅市场与经济信息处", 6,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10006", "农业厅农产品质量安全监管处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅农产品质量安全监管处兼挂行政审批办公室", 7,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10007", "农业厅计划财务处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅计划财务处", 8,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10008", "农业厅科技教育处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅计划财务处", 9,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10009", "农业厅对外经济合作处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅对外经济合作处", 10,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10010", "农业厅粮食油料作物处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅粮食油料作物处", 11,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10011", "农业厅糖料作物处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅糖料作物处", 12,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10012", "农业厅经济作物处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅经济作物处", 13,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10013", "农业厅机关党委", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅机关党委", 14,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10014", "农业厅离退休人员工作处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅离退休人员工作处", 15,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10015", "农业厅农村改革处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅农村改革处", 16,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10016", "农业厅驻厅纪检监察室", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅驻厅纪检监察室", 17,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10017", "农业厅农民人均纯收入倍增计划实施工作领导小组办公室", "450000",
					"GXNYT", "0771-8888888", "0771-8888888",
					"admin@gxny.gov.cn", "530022", "广西壮族自治区南宁市七星路135号",
					"农业厅农民人均纯收入倍增计划实施工作领导小组办公室", 18, "http://www.gxny.gov.cn",
					Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10018", "农业厅清洁田园活动领导小组办公室", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅清洁田园活动领导小组办公室", 19,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			dept = new Org("10019", "农业厅审计厅派出农水处", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅审计厅派出农水处", 20,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			dept.setParent(org);
			deptList.add(dept);

			org.setChildren(deptList);

			orgManager.saveOrg(org);

			// 直属机构
			List<Org> subOrgList=new ArrayList<Org>();
			org=null;
			org = new Org("20000", "农业厅直属单位", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅直属单位", 21,
					"http://www.gxny.gov.cn", Boolean.TRUE);

			Org subOrg = new Org("20001", "广西壮族自治区水产畜牧兽医局", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西壮族自治区水产畜牧兽医局", 22,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20002", "农业厅机关服务中心", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "农业厅机关服务中心", 23,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20003", "广西农业信息中心", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西农业信息中心", 24,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20004", "广西农村经济经营管理总站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西农村经济经营管理总站", 25,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20005", "广西农业外资项目管理中心", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西农业外资项目管理中心", 26,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20006", "广西绿色食品办公室", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西绿色食品办公室（广西优质农产品开发服务中心）",
					27, "http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20007", "广西种子管理局", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西种子管理局", 28,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20008", "广西水果生产技术指导总站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西水果生产技术指导总站", 29,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20009", "广西植保总站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西植保总站", 30,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20010", "广西农业技术推广总站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西农业技术推广总站", 31,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20011", "广西蚕业技术推广总站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西蚕业技术推广总站（广西蚕业科学研究院）", 32,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20012", "广西农业生态与资源保护总站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西农业生态与资源保护总站", 33,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20013", "广西土壤肥料工作站", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西土壤肥料工作站", 34,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20014", "广西桂林茶叶科学研究所", "450000", "GXNYT",
					"0773-8888888", "0773-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区桂林市135号", "广西桂林茶叶科学研究所", 35,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);
			
			subOrg = new Org("20015", "广西桂林茶叶科学研究所", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西桂林茶叶科学研究所", 36,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			subOrg = new Org("20016", "广西柑桔研究所", "450000", "GXNYT",
					"0771-8888888", "0771-8888888", "admin@gxny.gov.cn",
					"530022", "广西壮族自治区南宁市七星路135号", "广西柑桔研究所", 37,
					"http://www.gxny.gov.cn", Boolean.TRUE);
			subOrg.setParent(org);
			subOrgList.add(subOrg);

			org.setChildren(subOrgList);

			orgManager.saveOrg(org);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "初始化数据成功!");
		} catch (Exception e) {
			e.printStackTrace();

			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "初始化数据失败!");

		}
		return jsonObject;
	}

}
