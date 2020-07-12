package com.zzf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zzf.po.JobsWorkers;
import com.zzf.service.JobsWorkerService;

/**
 * 员工职位信息模块
 *
 */
@Controller
public class JobsWorkersController {

	@Autowired
	private JobsWorkerService jobsWorkersService;

	// 全部查询
	@RequestMapping("/JobsWorkerslist.action")
	public String JobsWorkerslist(Model model) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkers();
		model.addAttribute("JobsWorkersList", list);
		return "JobsWorkers/jobsWorkerslist";
	}

	// 按工号查询
	@RequestMapping("/JobsWorkerslistByWno.action")
	public String JobsWorkerslistByWno(Model model, String wno) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkersByWno(wno);
		model.addAttribute("JobsWorkersList", list);
		return "JobsWorkers/jobsWorkerslist";
	}

}
