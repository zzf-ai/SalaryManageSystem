package com.zzf.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.User;
import com.zzf.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zzf.po.Workers;
import com.zzf.service.WorkerService;

import javax.servlet.http.HttpSession;

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
	public String Workerslist(Model model) {
		List<Workers> list = workerService.findAllWorkers();
		model.addAttribute("workerList", list);
		return "workers/workerslist";
	}

	// �������ؼ��ʲ���
	@RequestMapping("/workerslistbyname.action")
	public String WorkerslistByWname(Model model, String wname) {
		List<Workers> list = workerService.findWorkersByWname(wname.trim());
		model.addAttribute("workerList", list);
		return "workers/workerslist";
	}

	// �����Ų���
	@RequestMapping("/workerslistbyno.action")
	public String WorkerslistByWno(Model model, String wno) {
		List<Workers> list = workerService.findWorkersByWno(wno.trim());
		model.addAttribute("workerList", list);
		return "workers/workerslist";
	}

	// ���
	@RequestMapping(value = "/workersinsert.action", method = RequestMethod.POST)
	public String WorkersInsert(Workers workers) {
		workerService.addWorkers(workers);
		return "redirect:workerslist.action";
	}

	// ɾ��
	@RequestMapping(value = "/workersdelete.action", method = RequestMethod.POST)
	public String WorkersDelete(String[] wnoArray) {
		//System.out.println();
		workerService.deleteWorkers(wnoArray);
		return "redirect:workerslist.action";
	}

	//����
	@RequestMapping(value = "/workersupdate.action", method = RequestMethod.POST)
	public String WorkersUpdate(Workers workers) {
		workerService.updateWorkers(workers);
		return "redirect:workerslist.action";
	}

	//�޸ĸ�����Ϣ
	@RequestMapping(value = "/workersupdate2.action", method = RequestMethod.POST)
	public String WorkersUpdate2(Workers workers, String usercode, String user_id, HttpSession session) {
		workerService.updateWorkers(workers);
		userService.changeUserCode(usercode,user_id);
		User user = userService.findUserCode(usercode);
		if (user != null) {
			// ���û�������ӵ�Session
			session.setAttribute("USER_SESSION", user);
		}
		return "redirect:toMyInfo.action";
	}

	//�첽��֤
	@RequestMapping(value = "/ajaxCheck3.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(@RequestBody JSONObject data) {
		String wno=data.getString("wno");
		System.out.println(wno);
		List<Workers> workers=workerService.findWorkersByWno(wno);
		System.out.println(workers);
		if((workers!=null&&workers.size()==1)){
			return "ok";
		}
		else
			return "false";
	}

}
