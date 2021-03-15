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
 * ��½ע��
 *
 */
@Controller
public class UserController {

	// ����ע��
	@Autowired
	private UserService userService;

	@Autowired
	private WorkerService workersService;

	@Autowired
	private WjService wjService;

	@Autowired
	private JobService jobService;

	// ȫ����ѯ
	@RequestMapping("/userslist.action")
	public String Userslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage, Model model) {
		Page<User> page = userService.findUsersByPage(currentPage);

		//�Բ������ҳ�����
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

	//��֤��
	@RequestMapping("/validateCode.action")
	public void validateCode(HttpServletRequest request, HttpServletResponse response){
		//�������ͣ�����ΪͼƬ
		response.setContentType("image/jpeg");
		//������Ӧͷ��Ϣ����֪�������Ҫ����
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("expires",-1);

		ValidateCode validateCode=new ValidateCode();
		try{
			//���ͼƬ
			validateCode.getRandomCode(request,response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	// �û���¼
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String usercode, String password,String validateCode, Model model, HttpSession session) {
		// ͨ���˺������ѯ�û�
		User user = userService.findUser(usercode, MD5Util.getMD5String(password));
		System.out.println(user);
		if (user != null) {
			String rightCode= (String) session.getAttribute(ValidateCode.VALIDATECODE);
			if(rightCode.equalsIgnoreCase(validateCode)){
				// ���û�������ӵ�Session
				user.setPassword(null);
				session.setAttribute("USER_SESSION", user);
				String auth=user.getAuthority();
				switch (auth.trim()){
					case "����":
						return "customer";
					case "ϵͳ����Ա":
						return "admin";
					case "��ͨԱ��"	:
						return "employee";
					case "��˾�߲�"	:
						return "leader";
				}
			}
			else{
				model.addAttribute("msg", "��֤���������");
				return "login";
			}
		}
		model.addAttribute("msg", "�˺Ż��������");
		return "login";
	}


	//�첽��֤�˺��Ƿ��Ѵ���
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


	//�첽��֤�����Ƿ��ѱ���
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
	 * ����������ת����ҳ��ķ���
	 */
	@RequestMapping(value="/toCustomer.action") public String toCustomer() { return "customer"; }
	@RequestMapping(value="/toAdmin.action") public String toAdmin() { return "admin"; }
	@RequestMapping(value="/toEmployee.action") public String toEmployee() { return "employee"; }
	@RequestMapping(value="/toLeader.action") public String toLeader() { return "leader"; }

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

	//��ת�޸�����ҳ��
	@RequestMapping(value = "/toChangePassword.action")
	public String toChangePassword() {
		return "changePassword";
	}

	//��ת������Ϣҳ��
	@RequestMapping(value = "/toMyInfo.action")
	public String MyInfo(Model model,HttpSession session) {
		User user= (User) session.getAttribute("USER_SESSION");
		List<Workers> list = workersService.findWorkersByWno(user.getWno());
		model.addAttribute("workerList", list);
		return "myinfo";
	}

	// ע��
	@RequestMapping(value = "/Register.action", method = RequestMethod.POST)
	public String Register(User user, Model model) {
		String wno=user.getWno();
		Wj wj = wjService.findWJByWno(wno);
		String jno = wj.getJno();
		List<Jobs> jobsByJno = jobService.findJobsByJno(jno);
		String jname = jobsByJno.get(0).getJname();
		if(jname.contains("����")){
			user.setAuthority("��˾�߲�");
		}else if(jname.contains("���")){
			user.setAuthority("����");
		}
		userService.insertUser(user);
		model.addAttribute("msg", "ע��ɹ�,���¼");
		return "login";
	}

	// ɾ��
	@RequestMapping("/Usersdelete.action")
	public String UsersDelete(String[] user_ids) {
		userService.deleteUser(user_ids);
		return "redirect:userslist.action";
	}

	//��Ȩ
	@RequestMapping(value = "/grant.action", method = RequestMethod.POST)
	public String grant(String authority,String user_id) {
		userService.grant(authority,user_id);
		return "redirect:userslist.action";
	}

	//�޸�����
	@RequestMapping(value = "/changePassword.action", method = RequestMethod.POST)
	public String changePassword(String password,String user_id) {
		userService.changePassword(MD5Util.getMD5String(password),user_id);
		return "redirect:login.action";
	}

	//���˺Ų�ѯ
	@RequestMapping(value = "/findUserCode.action")
	public String findUserCode2(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model,String usercode) {
		Page<User> page = userService.findUsersByUserCodeByPage(currentPage,usercode);

		//�Բ������ҳ�����
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

	// ���ݹ��Ų�ѯ
	@RequestMapping(value = "/findUserByWno.action")
	public String findUserByWno2(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model,String wno) {
		Page<User> page = userService.findUsersByWnoByPage(currentPage,wno);

		//�Բ������ҳ�����
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
