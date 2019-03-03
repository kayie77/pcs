package com.yunforge.cms.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yunforge.cms.service.InfoFileManager;
import com.yunforge.cms.service.InfoManager;
import com.yunforge.common.Constants;
import com.yunforge.common.util.Plupload;
import com.yunforge.common.util.PluploadUtil;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.security.ShiroUser;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class InfoFileController extends BaseController {
	final static Log log = LogFactory.getLog(InfoFileController.class);

	@Autowired
	private InfoManager infoManager;

	@Autowired
	private InfoFileManager infoFileManager;

	@MethodRemark(remark = "上传文件")
	@RequestMapping(value = "/cms/info/uploadFiles")
	public @ResponseBody
	JSONObject uploadFiles(Plupload plupload, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {

			plupload.setRequest(request);

			String path = request.getSession().getServletContext()
					.getRealPath("uploads");
			File dir = new File(path);

			log.info("路径:" + dir.getPath());

			PluploadUtil.upload(plupload, dir);
			if (PluploadUtil.isUploadFinish(plupload)) {
				log.info("已上传文件:" + plupload.getName() + "----");
			}

			jsonObject.put("jsonrpc", "2.0");
			jsonObject.put("result", "success");
			jsonObject.put("id", "id");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("jsonrpc", "2.0");
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("code", "101");
			errors.put("message", "上传失败");
			jsonObject.put("error", errors);
			jsonObject.put("id", "id");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "上传文件")
	@RequestMapping(value = "/cms/info/uploadFile")
	public void uploadFile(@RequestParam("upload") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		ShiroUser user = (ShiroUser) super.getCurrentUser();
		Random random = new Random();
		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		PrintWriter out;
		String fileName = multipartFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."))
				.toLowerCase();

		fileName = System.currentTimeMillis()
				+ String.valueOf(random.nextInt(10000)) + extName;
		String filePathName = request.getSession().getServletContext()
				.getRealPath("uploads" + File.separator + user.getUid());
		String ctx=request.getSession().getServletContext().getContextPath();
        log.info("地址:"+ctx+File.separator + "uploads"+ File.separator+user.getUid()+File.separator+fileName);
		String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("
				+ CKEditorFuncNum
				+ ", '"
				+ request.getScheme()+"://"+request.getRemoteHost()+":"+request.getRemotePort()+ctx+"/uploads/"+ user.getUid()+"/"+fileName
				+ "');</script>";
		try {
			File filePath = new File(filePathName);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}

			File tempFile = new File(filePathName + File.separator + fileName);
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			multipartFile.transferTo(tempFile);

			out = response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("上传异常!");
		}
	}

	@MethodRemark(remark = "浏览服务器图片文件")
	@RequestMapping(value = "/cms/info/browserImage", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/cms/info/browser";
	}

	@MethodRemark(remark = "删除信息")
	@RequestMapping(value = "/cms/info/deleteFile/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteInfo(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (id != null) {
				infoManager.removeInfos(new String[] { id });
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
}
