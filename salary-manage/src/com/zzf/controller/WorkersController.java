package com.zzf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzf.po.Workers;
import com.zzf.service.WorkerService;

/**
 * 员工个人信息模块
 *
 */
@Controller
public class WorkersController {

	@Autowired
	private WorkerService workerService;

	// 全部查询
	@RequestMapping("/workerslist.action")
	public String Workerslist(Model model) {
		List<Workers> list = workerService.findAllWorkers();
		model.addAttribute("workerList", list);
		return "workers/workerslist";
	}

	// 按姓名关键词查找
	@RequestMapping("/workerslistbyname.action")
	public String WorkerslistByWname(Model model, String wname) {
		List<Workers> list = workerService.findWorkersByWname(wname.trim());
		model.addAttribute("workerList", list);
		return "workers/workerslist";
	}

	// 按工号查找
	@RequestMapping("/workerslistbyno.action")
	public String WorkerslistByWno(Model model, String wno) {
		List<Workers> list = workerService.findWorkersByWno(wno.trim());
		model.addAttribute("workerList", list);
		return "workers/workerslist";
	}

	// 添加
	@RequestMapping(value = "/workerspreinsert.action", method = RequestMethod.GET)
	public String WorkersPreinsert() {
		return "workers/workersadd";
	}

	@RequestMapping(value = "/workersinsert.action", method = RequestMethod.POST)
	public String WorkersInsert(Workers workers) {
		workerService.addWorkers(workers);
		return "redirect:workerslist.action";
	}

	// 删除
	@RequestMapping(value = "/workersdelete.action", method = RequestMethod.POST)
	public String WorkersDelete(String[] wnoArray) {
		workerService.deleteWorkers(wnoArray);
		return "redirect:workerslist.action";
	}

	// 修改
	@RequestMapping(value = "/workerspreupdate.action", method = RequestMethod.GET)
	public String WorkersPreupdate(Workers workers, Model model) {
		model.addAttribute("workers", workers);
		return "workers/workersupdate";
	}

	@RequestMapping(value = "/workersupdate.action", method = RequestMethod.POST)
	public String WorkersUpdate(Workers workers) {
		workerService.updateWorkers(workers);
		return "redirect:workerslist.action";
	}

}
