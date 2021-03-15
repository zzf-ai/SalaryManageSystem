package com.zzf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zzf.pojo.User;

/**
 *拦截器
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		// 获取URL
		String url = request.getRequestURI();

		// 过滤一些请求
		if (url.indexOf("login.action") > 0 || url.indexOf("toRegister.action") > 0
				|| url.indexOf("Register.action") > 0||url.indexOf("ajaxCheck")>0 || url.indexOf("validateCode")>0) {
			return true;
		}

		// 获取session
		HttpSession session = request.getSession();
		// 获取登录信息
		User user = (User) session.getAttribute("USER_SESSION");
		if (user != null) {
			return true;
		}
		// 拦截给出提示信息，请求转发
		request.setAttribute("msg", "您还没有登录，请先登录");
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

}
