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
 * Ա��������Ϣģ��
 *
 */
@Controller
public class WorkersController {

	@Autowired
	private WorkerService workerService;

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
	@RequestMapping(value = "/workerspreinsert.action", method = RequestMethod.GET)
	public String WorkersPreinsert() {
		return "workers/workersadd";
	}

	@RequestMapping(value = "/workersinsert.action", method = RequestMethod.POST)
	public String WorkersInsert(Workers workers) {
		workerService.addWorkers(workers);
		return "redirect:workerslist.action";
	}

	// ɾ��
	@RequestMapping(value = "/workersdelete.action", method = RequestMethod.POST)
	public String WorkersDelete(String[] wnoArray) {
		workerService.deleteWorkers(wnoArray);
		return "redirect:workerslist.action";
	}

	// �޸�
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
