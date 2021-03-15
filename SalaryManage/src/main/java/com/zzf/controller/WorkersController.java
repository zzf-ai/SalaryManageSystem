package com.zzf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzf.pojo.Page;
import com.zzf.pojo.User;
import com.zzf.pojo.Workers;
import com.zzf.service.UserService;
import com.zzf.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * Ա��������Ϣģ��
 *
 */
@Controller
public class WorkersController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private UserService userService;


	// ȫ����ѯ
	@RequestMapping("/workerslist.action")
	public String Workerslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model) {

		Page<Workers> page = workerService.findWorkersByPage(currentPage);

		//�Բ������ҳ�����
		if(page.getDatas().size()==0&&currentPage!=1){
			page = workerService.findWorkersByPage(currentPage-1);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = workerService.findWorkersByPage(page.getTotalPage());
		}
		model.addAttribute("flag1",0);
		model.addAttribute("workerList", page);
		return "workers/workerslist";
	}

	// �������ؼ��ʲ���
	@RequestMapping("/workerslistbyname.action")
	public String WorkerslistByWname(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wname) {
		Page<Workers> page = workerService.findWorkersByWnameByPage(currentPage,wname.trim());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = workerService.findWorkersByWnameByPage(currentPage-1,wname.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = workerService.findWorkersByWnameByPage(page.getTotalPage(),wname.trim());
		}


		model.addAttribute("workerList", page);
		model.addAttribute("flag1",1);
		model.addAttribute("wname",wname);
		return "workers/workerslist";
	}

	// �����Ų���
	@RequestMapping("/workerslistbyno.action")
	public String WorkerslistByWno(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wno) {
		Page<Workers> page = workerService.findWorkersByWnoByPage(currentPage,wno.trim());
		if(page.getDatas().size()==0&&currentPage!=1){
			page = workerService.findWorkersByWnoByPage(currentPage-1,wno.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = workerService.findWorkersByWnoByPage(page.getTotalPage(),wno.trim());
		}
		model.addAttribute("workerList", page);
		model.addAttribute("flag1",2);
		model.addAttribute("wno",wno);
		return "workers/workerslist";
	}

	// ���
	@ResponseBody
	@RequestMapping(value = "/workersinsert.action", method = RequestMethod.POST)
	public String WorkersInsert(Workers workers) {
		workerService.addWorkers(workers);
		return "true";
	}

	// ɾ��
	@ResponseBody
	@RequestMapping(value = "/workersdelete.action", method = RequestMethod.POST)
	public String WorkersDelete(String[] wnoArray) {
		System.out.println(Arrays.toString(wnoArray));
		workerService.deleteWorkers(wnoArray);
		return "true";
	}

	//����
	@ResponseBody
	@RequestMapping(value = "/workersupdate.action", method = RequestMethod.POST)
	public String WorkersUpdate(Workers workers) {
		System.out.println(workers.toString());
		workerService.updateWorkers(workers);
		return "true";
	}

	//�޸ĸ�����Ϣ
	@RequestMapping(value = "/workersupdate2.action", method = RequestMethod.POST)
	public String WorkersUpdate2(Workers workers, String usercode, String user_id, HttpSession session) {
		workerService.updateWorkers(workers);
		userService.changeUserCode(usercode,user_id);
		User user = userService.findUserCode(usercode);
		if (user != null) {
			// ���û�������ӵ�Session
			user.setPassword(null);
			session.setAttribute("USER_SESSION", user);
		}
		return "redirect:toMyInfo.action";
	}

	//�첽��֤�����Ƿ��Ѵ���
	@RequestMapping(value = "/ajaxCheck3.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(HttpServletRequest request) {
		String param = request.getParameter("param");
		JSONObject jsonObject = JSON.parseObject(param);
		String wno=jsonObject.getString("wno");
		List<Workers> workers=workerService.findWorkersByWno(wno);
		if((workers!=null&&workers.size()==1)){
			return "false";
		}
		else {
			return "true";
		}
	}

}
