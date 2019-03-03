package com.yunforge.api.notice;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.api.dto.NoticeDto;
import com.yunforge.api.dto.NoticeDetailDto;
import com.yunforge.api.dto.ResultDto;
import com.yunforge.cms.service.InfoManager;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class NoticeApiController extends BaseController {
	final static Log log = LogFactory.getLog(NoticeApiController.class);

	@Autowired
	private InfoManager infoManager;

	@MethodRemark(remark = "移动端提醒")
	@RequestMapping(value = "/api/notice", method = RequestMethod.GET)
	public @ResponseBody
	NoticeDetailDto getNotice(HttpServletRequest request) {
		NoticeDetailDto noticeDetailDto = new NoticeDetailDto();
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null && currentUser.isAuthenticated()) {
			NoticeDto noticeDto = new NoticeDto();
			noticeDto.setAtmeCount(1);
			noticeDto.setMsgCount(3);
			noticeDto.setNewFansCount(3);
			noticeDto.setNewLikeCount(2);
			noticeDto.setReviewCount(3);
			noticeDetailDto.setNotice(noticeDto);

			ResultDto resultDto = new ResultDto();
			resultDto.setErrorCode(1);
			resultDto.setErrorMessage("");
			noticeDetailDto.setResult(resultDto);
		} else {
			NoticeDto noticeDto = new NoticeDto();
			noticeDto.setAtmeCount(0);
			noticeDto.setMsgCount(0);
			noticeDto.setNewFansCount(0);
			noticeDto.setNewLikeCount(0);
			noticeDto.setReviewCount(0);
			noticeDetailDto.setNotice(noticeDto);

			ResultDto resultDto = new ResultDto();
			resultDto.setErrorCode(0);
			resultDto.setErrorMessage("用户未登录");
			noticeDetailDto.setResult(resultDto);
		}
		return noticeDetailDto;
	}

}
