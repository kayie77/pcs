package com.yunforge.api.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.api.dto.ApiDTO;
import com.yunforge.api.dto.LoginUserDto;
import com.yunforge.api.dto.NoticeDto;
import com.yunforge.api.dto.ResultDto;
import com.yunforge.api.dto.UserDto;
import com.yunforge.base.model.User;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;
import com.yunforge.core.web.controller.BaseController;


@Controller
public class LoginApiController extends BaseController {
	final static Log log = LogFactory.getLog(LoginApiController.class);

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/api/login")
	public @ResponseBody
	ApiDTO login(String username, String password,
			HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
		LoginUserDto loginUser = new LoginUserDto();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		try {
			if (!subject.isAuthenticated()) {
				subject.login(token);
			}
			User user = userManager.findUserByUsername(username);
			Session session = subject.getSession();
			session.setAttribute(Constants.SESSION_USER_KEY, user);//设置session
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getFullName());
			userDto.setFans(0);
			userDto.setFavoritecount(0);
			userDto.setFollowers(0);
			userDto.setFrom(null);
			userDto.setGender("1");
			userDto.setLocation("");//user.getOrg().getOrgName()
			userDto.setPersonId(user.getPerson().getId());
		    if(StringUtils.isBlank(user.getAvatar())){
		    	userDto.setPortrait(req.getScheme()+"://"+req.getRemoteAddr()+":"+req.getServerPort()+"/"+req.getServletContext().getContextPath()+"/uploads/avatar80x80.jpg");
		    }else{
		    	userDto.setPortrait(user.getAvatar());
		    }
			loginUser.setUser(userDto);
		
			NoticeDto notice = new NoticeDto();
			notice.setAtmeCount(0);
			notice.setMsgCount(0);
			notice.setNewFansCount(0);
			notice.setNewLikeCount(0);
			notice.setReviewCount(0);
			loginUser.setNotice(notice);
			
			ResultDto result=new ResultDto();
			result.setErrorCode(1);
			result.setErrorMessage("登录成功!");
			loginUser.setResult(result);

		} catch (UnknownAccountException uae) {
			ResultDto result = new ResultDto();
			result.setErrorCode(0);
			result.setErrorMessage("用户名不存在!");
			loginUser.setResult(result);
			NoticeDto notice = new NoticeDto();
			notice.setAtmeCount(0);
			notice.setMsgCount(0);
			notice.setNewFansCount(0);
			notice.setNewLikeCount(0);
			notice.setReviewCount(0);
			loginUser.setNotice(notice);
		} catch (IncorrectCredentialsException ice) {
			ResultDto result = new ResultDto();
			result.setErrorCode(0);
			result.setErrorMessage("用户名对应的密码错误!");
			loginUser.setResult(result);
			NoticeDto notice = new NoticeDto();
			notice.setAtmeCount(0);
			notice.setMsgCount(0);
			notice.setNewFansCount(0);
			notice.setNewLikeCount(0);
			notice.setReviewCount(0);
			loginUser.setNotice(notice);
		} catch (LockedAccountException lae) {
			ResultDto result = new ResultDto();
			result.setErrorCode(0);
			result.setErrorMessage("登录用户被锁定，登录失败!");
			loginUser.setResult(result);
			NoticeDto notice = new NoticeDto();
			notice.setAtmeCount(0);
			notice.setMsgCount(0);
			notice.setNewFansCount(0);
			notice.setNewLikeCount(0);
			notice.setReviewCount(0);
			loginUser.setNotice(notice);
		} catch (ExcessiveAttemptsException eae) {
			ResultDto result = new ResultDto();
			result.setErrorCode(0);
			result.setErrorMessage("超过登录次数!");
			loginUser.setResult(result);
			NoticeDto notice = new NoticeDto();
			notice.setAtmeCount(0);
			notice.setMsgCount(0);
			notice.setNewFansCount(0);
			notice.setNewLikeCount(0);
			notice.setReviewCount(0);
			loginUser.setNotice(notice);
		} catch (AuthenticationException  ae) {
			ResultDto result = new ResultDto();
			result.setErrorCode(0);
			result.setErrorMessage("认证失败!");
			loginUser.setResult(result);
			NoticeDto notice = new NoticeDto();
			notice.setAtmeCount(0);
			notice.setMsgCount(0);
			notice.setNewFansCount(0);
			notice.setNewLikeCount(0);
			notice.setReviewCount(0);
			loginUser.setNotice(notice);
		}

		ApiDTO apiDTO = new ApiDTO();
		apiDTO.setCode("1");
		apiDTO.setData(loginUser);
		apiDTO.setMsg("操作成功！");
		return apiDTO;
	}

}
