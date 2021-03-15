package com.zzf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzf.pojo.Jobs;
import com.zzf.pojo.Page;
import com.zzf.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
	public String Jobslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model,HttpSession session) {
		Page<Jobs> page = jobService.findJobsByPage(currentPage);

		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobService.findJobsByPage(currentPage-1);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = jobService.findJobsByPage(page.getTotalPage());
		}
		model.addAttribute("jobList", page);
		model.addAttribute("flag2",0);
		return "jobs/jobslist";
	}


	//异步验证职位编号是否已存在
	@RequestMapping(value = "/ajaxCheck4.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(HttpServletRequest request) {
		String param = request.getParameter("param");
		JSONObject jsonObject = JSON.parseObject(param);
		String jno=jsonObject.getString("jno");
		List<Jobs> jobs=jobService.findJobsByJno(jno);
		if(jobs!=null&&jobs.size()==1){
			return "false";
		}
		else {
			return "true";
		}
	}


	// 职位查找
	@RequestMapping("/jobslistbyname.action")
	public String JobslistByWname(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jname) {
		Page<Jobs> page = jobService.findJobsByJname(currentPage,jname.trim());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobService.findJobsByJname(currentPage-1,jname.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = jobService.findJobsByJname(page.getTotalPage(),jname.trim());
		}
		model.addAttribute("jobList", page);
		model.addAttribute("flag2",1);
		model.addAttribute("jname",jname);
		return "jobs/jobslist";
	}


	// 按部门查找
	@RequestMapping("/jobslistbyJdept.action")
	public String jobslistbyJdept(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jdept) {
		Page<Jobs> page = jobService.findJobsByJdept(currentPage,jdept.trim());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobService.findJobsByJdept(currentPage-1,jdept.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = jobService.findJobsByJdept(page.getTotalPage(),jdept.trim());
		}
		model.addAttribute("jobList", page);
		model.addAttribute("flag2",2);
		model.addAttribute("jdept",jdept);
		return "jobs/jobslist";
	}


	//添加职位
	@ResponseBody
	@RequestMapping(value = "/jobsinsert.action", method = RequestMethod.POST)
	public String JobsInsert(Jobs jobs) {
		jobService.addJobs(jobs);
		return "true";
	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "/jobsdelete.action", method = RequestMethod.POST)
	public String JobsDelete(String[] jnoArray) {
		jobService.deleteJobs(jnoArray);
		return "true";
	}


	//修改职位信息
	@ResponseBody
	@RequestMapping(value = "/jobsupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Jobs jobs) {
		jobService.updateJobs(jobs);
		return "true";
	}
}
