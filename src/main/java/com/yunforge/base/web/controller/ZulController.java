package com.yunforge.base.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunforge.common.util.StringUtil;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class ZulController extends BaseController {
	final static Log log = LogFactory.getLog(ZulController.class);

	@RequestMapping(value = "/office/zul/p1420991153029_AutoCreateZul_16", method = RequestMethod.GET)
	public void p1420991153029_AutoCreateZul_16(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//divcode="+divcode+"&agrresidxCode="+agrresidx+"&indexCode=
		String divcode = request.getParameter("divcode");
		String agrresidxCode = request.getParameter("agrresidxCode");
		String indexCode = request.getParameter("indexCode");
		
		try {
//			FileReader fr = new FileReader("D:/Yunforge/YunforgeBI/WebRoot/p1420991153029_AutoCreateZul_16.zul");
//			BufferedReader br = new BufferedReader(fr);
//			String str = null;
//			StringBuffer resultsb = new StringBuffer();
//			while((str = br.readLine()) != null) {
//				resultsb.append(str+"\n");
//			}
//			br.close();
//			fr.close();
			
			File file = new File("D:/Yunforge/YunforgeBI/WebRoot/p1420991153029_AutoCreateZul_16.zul");
			byte[] bytes = new byte[(int)file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(bytes);
			fis.close();
			
			
			String result = new String(bytes);
//			String result = resultsb.toString();
			
			if(StringUtil.isEmpty(divcode)) {
				result = result.replaceAll("%divcode%", "%%");
			} else {
				result = result.replaceAll("%divcode%", divcode);
			}
			
//			if(StringUtil.isEmpty(agrresidxCode) && StringUtil.isEmpty(indexCode)) {
//				result = result.replaceAll("%agrresidxcode%", "%%");
//			} else if(!StringUtil.isEmpty(agrresidxCode) && StringUtil.isEmpty(indexCode)) {
//				result = result.replaceAll("%agrresidxcode%", agrresidxCode + "%");
//			} else {
//				result = result.replaceAll("%agrresidxcode%", agrresidxCode + indexCode);
//			}
			
			if(StringUtil.isEmpty(agrresidxCode)) {
				result = result.replaceAll("%agrresidxcode%", "%%");
			} else {
				result = result.replaceAll("%agrresidxcode%", agrresidxCode + "%");
			}
			
			String filename = "p1420991153029_AutoCreateZul_16_" +new Date().getTime()+ ".zul";
//			FileWriter fw = new FileWriter("D:/Yunforge/YunforgeBI/WebRoot/" + filename);
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(result);
//			bw.close();
//			fw.close();
			FileOutputStream fos = new FileOutputStream("D:/Yunforge/YunforgeBI/WebRoot/" + filename);
			fos.write(result.getBytes());
			fos.close();
			
			Thread.sleep(2000);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("filename", filename);
			
			String json = jsonObject.toString();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json);
		} catch(Exception e) {
			
		}
	}
	
}