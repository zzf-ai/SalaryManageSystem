package com.zzf.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.Jobs;
import com.zzf.po.Wj;
import com.zzf.po.Workers;
import com.zzf.service.JobService;
import com.zzf.service.WjService;
import com.zzf.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zzf.po.JobsWorkers;
import com.zzf.service.JobsWorkerService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 员工职位信息模块,负责人事调动
 *
 */
@Controller
public class JobsWorkersController {

	@Autowired
	private WjService wjService;


	@Autowired
	private JobsWorkerService jobsWorkersService;

	@Autowired
	private JobService jobService;

	@Autowired
	private WorkerService workerService;


	// 全部查询
	@RequestMapping("/JobsWorkerslist.action")
	public String JobsWorkerslist(Model model) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkers();
		model.addAttribute("JobsWorkersList", list);
		List<Jobs> jobs=jobService.findAllJobs();
		model.addAttribute("Jobs", jobs);
		List<Workers> workers=workerService.findAllWorkers();
		model.addAttribute("Workers", workers);
		return "JobsWorkers/jobsWorkerslist";
	}
	//结算模块控制器
	@RequestMapping("/JobsWorkerslist2.action")
	public String JobsWorkerslist2(Model model) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkers2();
		model.addAttribute("JobsWorkersList", list);
		return "WSalarys/Settlement";
	}

	// 按工号查询
	@RequestMapping("/JobsWorkerslistByWno.action")
	public String JobsWorkerslistByWno(Model model, String wno) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkersByWno(wno);
		model.addAttribute("JobsWorkersList", list);
		return "JobsWorkers/jobsWorkerslist";
	}

	// 按部门查询
	@RequestMapping("/JobsWorkerslistByJdept.action")
	public String JobsWorkerslistByJdept(Model model, String jdept) {
		List<JobsWorkers> list = jobsWorkersService.findJobsWithWorkersByJdept(jdept);
		model.addAttribute("JobsWorkersList", list);
		return "JobsWorkers/jobsWorkerslist";
	}
	//添加
	@RequestMapping(value = "/insertWj.action", method = RequestMethod.POST)
	public String insertWj(Wj wj) {
		String s=wj.getWno().trim().split(",")[0];
		String str=wj.getJno().trim().split(",")[0];
		System.out.println(s);
		System.out.println(str);
		//wj.setJno(wj.getJno().split(" ")[0]);
		wj.setWno(s);
		wj.setJno(str);
		wjService.addWj(wj);
		return "redirect:JobsWorkerslist.action";
	}

	// 删除
	@RequestMapping(value = "/wjdelete.action", method = RequestMethod.POST)
	public String JobsDelete(Integer[] id) {
		wjService.deleteWj(id);
		return "redirect:JobsWorkerslist.action";
	}

	//修改
	@RequestMapping(value = "/wjupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Wj wj) {
		String str1=wj.getWno().trim().split(",")[0];
		String str2=wj.getJno().trim().split(",")[0];
		System.out.println(str1);
		System.out.println(str2);
		//wj.setJno(wj.getJno().split(" ")[0]);
		wj.setWno(str1);
		wj.setJno(str2);
		wjService.updateWj(wj);
		return "redirect:JobsWorkerslist.action";
	}
}
