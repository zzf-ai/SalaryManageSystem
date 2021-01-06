package com.zzf.controller;

import java.util.Arrays;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.Page;
import com.zzf.po.User;
import com.zzf.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zzf.po.Workers;
import com.zzf.service.WorkerService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 员工个人信息模块
 *
 */
@Controller
public class WorkersController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private UserService userService;


	// 全部查询
	@RequestMapping("/workerslist.action")
	public String Workerslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model) {
		//List<Workers> list = workerService.findAllWorkers();
		Page<Workers> page = workerService.findWorkersByPage(currentPage);
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = workerService.findWorkersByPage(currentPage+1);
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = workerService.findWorkersByPage(currentPage-1);
		}
		model.addAttribute("flag1",0);
		model.addAttribute("workerList", page);
		return "workers/workerslist";
	}

	// 按姓名关键词查找
	@RequestMapping("/workerslistbyname.action")
	public String WorkerslistByWname(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wname) {
		Page<Workers> page = workerService.findWorkersByWnameByPage(currentPage,wname.trim());
		System.out.println(page.getTotalPage());
		/*if(page.getDatas().size()==page.getPageSize()&&currentPage!=1){
			page = workerService.findWorkersByWnameByPage(currentPage+1,wname.trim());
		}*/

		if(page.getDatas().size()==0&&currentPage!=1){
			page = workerService.findWorkersByWnameByPage(currentPage-1,wname.trim());
		}
		model.addAttribute("workerList", page);
		model.addAttribute("flag1",1);
		model.addAttribute("wname",wname);
		return "workers/workerslist";
	}

	// 按工号查找
	@RequestMapping("/workerslistbyno.action")
	public String WorkerslistByWno(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wno) {
		Page<Workers> page = workerService.findWorkersByWnoByPage(currentPage,wno.trim());
		model.addAttribute("workerList", page);
		model.addAttribute("flag1",2);
		return "workers/workerslist";
	}

	// 添加
	@ResponseBody
	@RequestMapping(value = "/workersinsert.action", method = RequestMethod.POST)
	public String WorkersInsert(Workers workers) {
		workerService.addWorkers(workers);
		return "true";
	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "/workersdelete.action", method = RequestMethod.POST)
	public String WorkersDelete(String[] wnoArray) {
		System.out.println(Arrays.toString(wnoArray));
		workerService.deleteWorkers(wnoArray);
		//return "redirect:workerslist.action";
		return "true";
	}

	//更新
	@ResponseBody
	@RequestMapping(value = "/workersupdate.action", method = RequestMethod.POST)
	public String WorkersUpdate(Workers workers) {
		System.out.println(workers.toString());
		workerService.updateWorkers(workers);
		return "true";
	}

	//修改个人信息
	@RequestMapping(value = "/workersupdate2.action", method = RequestMethod.POST)
	public String WorkersUpdate2(Workers workers, String usercode, String user_id, HttpSession session) {
		workerService.updateWorkers(workers);
		userService.changeUserCode(usercode,user_id);
		User user = userService.findUserCode(usercode);
		if (user != null) {
			// 将用户对象添加到Session
			session.setAttribute("USER_SESSION", user);
		}
		return "redirect:toMyInfo.action";
	}

	//异步验证
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
