package com.zzf.controller;


import com.zzf.Util.ValidateCode;
import com.zzf.po.*;
import com.zzf.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
	public String Userslist(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("userList", list);
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
		User user = userService.findUser(usercode, password);
		System.out.println(user);
		if (user != null) {
			String rightCode= (String) session.getAttribute(ValidateCode.VALIDATECODE);
			if(rightCode.equalsIgnoreCase(validateCode)){
				// ���û�������ӵ�Session
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


	//�첽��֤
	@RequestMapping(value = "/ajaxCheck.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(@RequestBody JSONObject data) {
		String usercode=data.getString("usercode");
		System.out.println(usercode);
		User user=userService.findUserCode(usercode);
		System.out.println(user);
		if(user!=null){
			return "ok";
		}
		else
			return "false";
	}


	@RequestMapping(value = "/ajaxCheck2.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam2(@RequestBody JSONObject data) {
		String wno=data.getString("wno");
		System.out.println(wno);
		User user=userService.findUserByWno(wno);
		List<Workers> workers = workersService.findWorkersByWno(wno);
		System.out.println(user);
		if(user!=null){
			return "ok";
		}
		else if(workers.size()==0){
			return "not";
		}
		else
			return "false";
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
		userService.changePassword(password,user_id);
		return "redirect:login.action";
	}

	// ��ѯȫ��
	/*@RequestMapping(value = "/findAll.action", method = RequestMethod.POST)
	public String findAll(Model model) {
		List<User> userList=userService.findAll();
		model.addAttribute("userList", userList);
		return "redirect:userslist.action";
	}*/
	//���˺Ų�ѯ
	/*@RequestMapping(value = "/findUserCode.action", method = RequestMethod.POST)
	public String findUserCode(Model model,String usercode) {
		List<User> userList=new ArrayList<>();
		userList.add(userService.findUserCode(usercode));
		model.addAttribute("userList",userList);
		return "redirect:userslist.action";
	}*/
	@RequestMapping(value = "/findUserCode.action")
	public String findUserCode2(Model model,String usercode) {
		List<User> userList=new ArrayList<>();
		userList.add(userService.findUserCode(usercode));
		model.addAttribute("userList",userList);
		return "users/userslist";
	}

	// ���ݹ��Ų�ѯ
	/*@RequestMapping(value = "/findUserByWno.action", method = RequestMethod.POST)
	public String findUserByWno(Model model,String wno) {
		List<User> userList=new ArrayList<>();
		userList.add(userService.findUserByWno(wno));
		model.addAttribute("userList",userList);
		return "redirect:userslist.action";
	}*/
	@RequestMapping(value = "/findUserByWno.action")
	public String findUserByWno2(Model model,String wno) {
		List<User> userList=new ArrayList<>();
		userList.add(userService.findUserByWno(wno));
		model.addAttribute("userList",userList);
		return "users/userslist";
	}


}
