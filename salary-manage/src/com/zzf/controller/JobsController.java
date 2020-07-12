package com.zzf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzf.po.Jobs;
import com.zzf.service.JobService;

/**
 * ��˾ְλ��Ϣ����ģ��
 *
 */
@Controller
public class JobsController {

	@Autowired
	private JobService jobService;

	// ��ѯȫ����Ϣ
	@RequestMapping("/jobslist.action")
	public String Jobslist(Model model) {
		List<Jobs> list = jobService.findAllJobs();
		System.out.println(list);
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}

	// �ؼ��ʲ���
	@RequestMapping("/jobslistbyname.action")
	public String JobslistByWname(Model model, String jname) {
		System.out.println(jname);
		List<Jobs> list = jobService.findJobsByJname(jname.trim());
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}

	// �����ʲ���
	@RequestMapping("/jobslistbysalary.action")
	public String JobslistByJsalary(Model model, String jsalary) {
		List<Jobs> list = jobService.findJobsByJsalary(Float.parseFloat(jsalary.trim()));
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}

	// ���
	@RequestMapping(value = "/jobspreinsert.action", method = RequestMethod.GET)
	public String JobsPreinsert() {
		return "jobs/jobsadd";
	}

	@RequestMapping(value = "/jobsinsert.action", method = RequestMethod.POST)
	public String JobsInsert(Jobs jobs) {
		jobService.addJobs(jobs);
		return "redirect:jobslist.action";
	}

	// ɾ��
	@RequestMapping(value = "/jobsdelete.action", method = RequestMethod.POST)
	public String JobsDelete(String[] jnoArray) {
		jobService.deleteJobs(jnoArray);
		return "redirect:jobslist.action";
	}

	// ����
	@RequestMapping(value = "/jobspreupdate.action", method = RequestMethod.GET)
	public String JobsPreupdate(Jobs Jobs, Model model) {
		model.addAttribute("Jobs", Jobs);
		return "jobs/jobsupdate";
	}

	@RequestMapping(value = "/jobsupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Jobs jobs) {
		jobService.updateJobs(jobs);
		return "redirect:jobslist.action";
	}
}
