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
 * 登陆注册
 *
 */
@Controller
public class UserController {

	// 依赖注入
	@Autowired
	private UserService userService;

	// 用户登录
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String usercode, String password, Model model, HttpSession session) {
		// 通过账号密码查询用户
		User user = userService.findUser(usercode, password);
		if (user != null) {
			// 将用户对象添加到Session
			session.setAttribute("USER_SESSION", user);
			// 跳转到主页面
			return "customer";
		}
		model.addAttribute("msg", "账号或密码错误！");
		return "login";
	}
	/*
	 * //模拟其它类中跳转到客户主页面的方法
	 * 
	 * @RequestMapping(value="/toCustomer.action") public String toCustomer() {
	 * return "customer"; }
	 */

	// 退出登录
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
		// 清除session
		session.invalidate();
		// 重定向
		return "redirect:login.action";
	}

	/**
	 * 用于用户向登录页面跳转
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// 跳转注册界面
	@RequestMapping(value = "/toRegister.action")
	public String toRegister() {
		return "register";
	}

	// 注册
	@RequestMapping(value = "/Register.action", method = RequestMethod.POST)
	public String Register(String usercode, String password, Model model) {

		User user1 = userService.findUserCode(usercode);
		if (user1 != null) {
			model.addAttribute("msg", "用户已存在！");
			return "register";
		}
		userService.insertUser(usercode, password);
		model.addAttribute("msg", "注册成功,请登录");
		return "login";
	}
}
