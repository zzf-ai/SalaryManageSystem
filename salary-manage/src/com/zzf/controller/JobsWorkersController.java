package com.zzf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zzf.po.JobsWorkers;
import com.zzf.service.JobsWorkerService;

/**
 * Ա��ְλ��Ϣģ��
 *
 */
@Controller
public class JobsWorkersController {

	@Autowired
	private JobsWorkerService jobsWorkersService;

	// ȫ����ѯ
	@RequestMapping("/JobsWorkerslist.action")
	public String JobsWorkerslist(Model model) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkers();
		model.addAttribute("JobsWorkersList", list);
		return "JobsWorkers/jobsWorkerslist";
	}

	// �����Ų�ѯ
	@RequestMapping("/JobsWorkerslistByWno.action")
	public String JobsWorkerslistByWno(Model model, String wno) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkersByWno(wno);
		model.addAttribute("JobsWorkersList", list);
		return "JobsWorkers/jobsWorkerslist";
	}

}
