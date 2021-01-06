package com.zzf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zzf.po.User;

/**
 *������
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO �Զ����ɵķ������

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		// ��ȡURL
		String url = request.getRequestURI();
		// ���س��˵�¼��ע��֮�����������
		if (url.indexOf("/login.action") > 0 || url.indexOf("/toRegister.action") > 0
				|| url.indexOf("/Register.action") > 0||url.indexOf("/ajaxCheck")>0||url.indexOf("/validateCode")>0) {
			return true;
		}
		// ��ȡsession
		HttpSession session = request.getSession();
		// ��ȡ��¼��Ϣ
		User user = (User) session.getAttribute("USER_SESSION");
		if (user != null) {
			return true;
		}
		// ���ظ�����ʾ��Ϣ������ת��
		request.setAttribute("msg", "����û�е�¼�����ȵ�¼");
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

}
