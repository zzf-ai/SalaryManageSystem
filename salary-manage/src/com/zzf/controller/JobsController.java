package com.zzf.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.User;
import com.zzf.po.Workers;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zzf.po.Jobs;
import com.zzf.service.JobService;

import javax.servlet.http.HttpSession;

/**
 * 公司职位信息管理模块
 *
 */
@Controller
public class JobsController {

	@Autowired
	private JobService jobService;


	// 查询全部信息
	@RequestMapping("/jobslist.action")
	public String Jobslist(Model model,HttpSession session) {
		List<Jobs> list = jobService.findAllJobs();
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}


	//异步验证
	@RequestMapping(value = "/ajaxCheck4.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(@RequestBody JSONObject data) {
		String jno=data.getString("jno");
		List<Jobs> jobs=jobService.findJobsByJno(jno);
		if(jobs!=null&&jobs.size()==1){
			return "ok";
		}
		else
			return "false";
	}

	// 职位查找
	@RequestMapping("/jobslistbyname.action")
	public String JobslistByWname(Model model, String jname,HttpSession session) {
		List<Jobs> list = jobService.findJobsByJname(jname.trim());
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}

	// 按部门查找
	@RequestMapping("/jobslistbyJdept.action")
	public String jobslistbyJdept(Model model, String jdept, HttpSession session) {
		List<Jobs> list = jobService.findJobsByJdept(jdept);
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}


	//添加职位
	@RequestMapping(value = "/jobsinsert.action", method = RequestMethod.POST)
	public String JobsInsert(Jobs jobs) {
		jobService.addJobs(jobs);
		return "redirect:jobslist.action";
	}

	// 删除
	@RequestMapping(value = "/jobsdelete.action", method = RequestMethod.POST)
	public String JobsDelete(String[] jnoArray) {
		jobService.deleteJobs(jnoArray);
		return "redirect:jobslist.action";
	}


	//修改职位信息
	@RequestMapping(value = "/jobsupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Jobs jobs) {
		jobService.updateJobs(jobs);
		return "redirect:jobslist.action";
	}
}
