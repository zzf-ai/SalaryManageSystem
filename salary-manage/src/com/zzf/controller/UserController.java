package com.zzf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzf.po.User;
import com.zzf.service.UserService;

/**
 * ��½ע��
 *
 */
@Controller
public class UserController {

	// ����ע��
	@Autowired
	private UserService userService;

	// �û���¼
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String usercode, String password, Model model, HttpSession session) {
		// ͨ���˺������ѯ�û�
		User user = userService.findUser(usercode, password);
		if (user != null) {
			// ���û�������ӵ�Session
			session.setAttribute("USER_SESSION", user);
			// ��ת����ҳ��
			return "customer";
		}
		model.addAttribute("msg", "�˺Ż��������");
		return "login";
	}
	/*
	 * //ģ������������ת���ͻ���ҳ��ķ���
	 * 
	 * @RequestMapping(value="/toCustomer.action") public String toCustomer() {
	 * return "customer"; }
	 */

	// �˳���¼
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
		// ���session
		session.invalidate();
		// �ض���
		return "redirect:login.action";
	}

	/**
	 * �����û����¼ҳ����ת
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// ��תע�����
	@RequestMapping(value = "/toRegister.action")
	public String toRegister() {
		return "register";
	}

	// ע��
	@RequestMapping(value = "/Register.action", method = RequestMethod.POST)
	public String Register(String usercode, String password, Model model) {

		User user1 = userService.findUserCode(usercode);
		if (user1 != null) {
			model.addAttribute("msg", "�û��Ѵ��ڣ�");
			return "register";
		}
		userService.insertUser(usercode, password);
		model.addAttribute("msg", "ע��ɹ�,���¼");
		return "login";
	}
}
