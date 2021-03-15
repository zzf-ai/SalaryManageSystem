package com.zzf.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzf.Util.MD5Util;
import com.zzf.Util.ValidateCode;
import com.zzf.pojo.*;
import com.zzf.service.JobService;
import com.zzf.service.UserService;
import com.zzf.service.WjService;
import com.zzf.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登陆注册
 *
 */
@Controller
public class UserController {

	// 依赖注入
	@Autowired
	private UserService userService;

	@Autowired
	private WorkerService workersService;

	@Autowired
	private WjService wjService;

	@Autowired
	private JobService jobService;

	// 全部查询
	@RequestMapping("/userslist.action")
	public String Userslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage, Model model) {
		Page<User> page = userService.findUsersByPage(currentPage);

		//对不合理的页面控制
		if(page.getDatas().size()==0&&currentPage!=1){
			page = userService.findUsersByPage(currentPage-1);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = userService.findUsersByPage(page.getTotalPage());
		}
		model.addAttribute("flag0",0);
		model.addAttribute("userList", page);
		return "users/userslist";
	}

	//验证码
	@RequestMapping("/validateCode.action")
	public void validateCode(HttpServletRequest request, HttpServletResponse response){
		//设置类型，内容为图片
		response.setContentType("image/jpeg");
		//设置响应头信息，告知浏览器不要缓存
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("expires",-1);

		ValidateCode validateCode=new ValidateCode();
		try{
			//输出图片
			validateCode.getRandomCode(request,response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	// 用户登录
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String usercode, String password,String validateCode, Model model, HttpSession session) {
		// 通过账号密码查询用户
		User user = userService.findUser(usercode, MD5Util.getMD5String(password));
		System.out.println(user);
		if (user != null) {
			String rightCode= (String) session.getAttribute(ValidateCode.VALIDATECODE);
			if(rightCode.equalsIgnoreCase(validateCode)){
				// 将用户对象添加到Session
				user.setPassword(null);
				session.setAttribute("USER_SESSION", user);
				String auth=user.getAuthority();
				switch (auth.trim()){
					case "财务":
						return "customer";
					case "系统管理员":
						return "admin";
					case "普通员工"	:
						return "employee";
					case "公司高层"	:
						return "leader";
				}
			}
			else{
				model.addAttribute("msg", "验证码输入错误！");
				return "login";
			}
		}
		model.addAttribute("msg", "账号或密码错误！");
		return "login";
	}


	//异步验证账号是否已存在
	@RequestMapping(value = "/ajaxCheck.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(HttpServletRequest request) {
		String param = request.getParameter("param");
		JSONObject jsonObject = JSON.parseObject(param);
		String usercode=jsonObject.getString("usercode");

		System.out.println(usercode);
		User user=userService.findUserCode(usercode);
		System.out.println(user);
		if(user!=null){
			return "false";
		}
		else {
			return "true";
		}
	}


	//异步验证工号是否已被绑定
	@RequestMapping(value = "/ajaxCheck2.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam2(HttpServletRequest request) {
		String param = request.getParameter("param");
		JSONObject jsonObject = JSON.parseObject(param);
		String wno=jsonObject.getString("wno");

		System.out.println(wno);
		User user=userService.findUserByWno(wno);
		List<Workers> workers = workersService.findWorkersByWno(wno);
		System.out.println(user);
		if(user!=null){
			return "true";
		}
		else if(workers.size()==0){
			return "not";
		}
		else {
			return "false";
		}
	}

	/*
	 * 其它类中跳转到主页面的方法
	 */
	@RequestMapping(value="/toCustomer.action") public String toCustomer() { return "customer"; }
	@RequestMapping(value="/toAdmin.action") public String toAdmin() { return "admin"; }
	@RequestMapping(value="/toEmployee.action") public String toEmployee() { return "employee"; }
	@RequestMapping(value="/toLeader.action") public String toLeader() { return "leader"; }

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

	//跳转修改密码页面
	@RequestMapping(value = "/toChangePassword.action")
	public String toChangePassword() {
		return "changePassword";
	}

	//跳转个人信息页面
	@RequestMapping(value = "/toMyInfo.action")
	public String MyInfo(Model model,HttpSession session) {
		User user= (User) session.getAttribute("USER_SESSION");
		List<Workers> list = workersService.findWorkersByWno(user.getWno());
		model.addAttribute("workerList", list);
		return "myinfo";
	}

	// 注册
	@RequestMapping(value = "/Register.action", method = RequestMethod.POST)
	public String Register(User user, Model model) {
		String wno=user.getWno();
		Wj wj = wjService.findWJByWno(wno);
		String jno = wj.getJno();
		List<Jobs> jobsByJno = jobService.findJobsByJno(jno);
		String jname = jobsByJno.get(0).getJname();
		if(jname.contains("经理")){
			user.setAuthority("公司高层");
		}else if(jname.contains("会计")){
			user.setAuthority("财务");
		}
		userService.insertUser(user);
		model.addAttribute("msg", "注册成功,请登录");
		return "login";
	}

	// 删除
	@RequestMapping("/Usersdelete.action")
	public String UsersDelete(String[] user_ids) {
		userService.deleteUser(user_ids);
		return "redirect:userslist.action";
	}

	//授权
	@RequestMapping(value = "/grant.action", method = RequestMethod.POST)
	public String grant(String authority,String user_id) {
		userService.grant(authority,user_id);
		return "redirect:userslist.action";
	}

	//修改密码
	@RequestMapping(value = "/changePassword.action", method = RequestMethod.POST)
	public String changePassword(String password,String user_id) {
		userService.changePassword(MD5Util.getMD5String(password),user_id);
		return "redirect:login.action";
	}

	//按账号查询
	@RequestMapping(value = "/findUserCode.action")
	public String findUserCode2(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model,String usercode) {
		Page<User> page = userService.findUsersByUserCodeByPage(currentPage,usercode);

		//对不合理的页面控制
		if(page.getDatas().size()==0&&currentPage!=1){
			page = userService.findUsersByUserCodeByPage(currentPage-1,usercode);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = userService.findUsersByUserCodeByPage(page.getTotalPage(),usercode);
		}
		model.addAttribute("flag0",1);
		model.addAttribute("userList", page);
		model.addAttribute("usercode", usercode);
		return "users/userslist";
	}

	// 根据工号查询
	@RequestMapping(value = "/findUserByWno.action")
	public String findUserByWno2(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model,String wno) {
		Page<User> page = userService.findUsersByWnoByPage(currentPage,wno);

		//对不合理的页面控制
		if(page.getDatas().size()==0&&currentPage!=1){
			page = userService.findUsersByWnoByPage(currentPage-1,wno);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = userService.findUsersByWnoByPage(page.getTotalPage(),wno);
		}
		model.addAttribute("flag0",2);
		model.addAttribute("userList", page);
		model.addAttribute("wno", wno);
		return "users/userslist";
	}


}
