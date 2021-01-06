package com.zzf.controller;

import java.nio.file.Path;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.Page;
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
 * ��˾ְλ��Ϣ����ģ��
 *
 */
@Controller
public class JobsController {

	@Autowired
	private JobService jobService;


	// ��ѯȫ����Ϣ
	@RequestMapping("/jobslist.action")
	public String Jobslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model,HttpSession session) {
		Page<Jobs> page = jobService.findJobsByPage(currentPage);
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = jobService.findJobsByPage(currentPage+1);
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobService.findJobsByPage(currentPage-1);
		}
		model.addAttribute("jobList", page);
		model.addAttribute("flag2",0);
		return "jobs/jobslist";
	}


	//�첽��֤
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

	// ְλ����
	@RequestMapping("/jobslistbyname.action")
	public String JobslistByWname(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jname) {
		Page<Jobs> page = jobService.findJobsByJname(currentPage,jname.trim());
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = jobService.findJobsByJname(currentPage+1,jname.trim());
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobService.findJobsByJname(currentPage-1,jname.trim());
		}
		model.addAttribute("jobList", page);
		model.addAttribute("flag2",1);
		return "jobs/jobslist";
	}

	// �����Ų���
	@RequestMapping("/jobslistbyJdept.action")
	public String jobslistbyJdept(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jdept) {
		Page<Jobs> page = jobService.findJobsByJdept(currentPage,jdept.trim());
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = jobService.findJobsByJdept(currentPage+1,jdept.trim());
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobService.findJobsByJdept(currentPage-1,jdept.trim());
		}
		model.addAttribute("jobList", page);
		model.addAttribute("flag2",2);
		return "jobs/jobslist";
	}


	//���ְλ
	@ResponseBody
	@RequestMapping(value = "/jobsinsert.action", method = RequestMethod.POST)
	public String JobsInsert(Jobs jobs) {
		jobService.addJobs(jobs);
		return "true";
	}

	// ɾ��
	@ResponseBody
	@RequestMapping(value = "/jobsdelete.action", method = RequestMethod.POST)
	public String JobsDelete(String[] jnoArray) {
		jobService.deleteJobs(jnoArray);
		return "true";
	}


	//�޸�ְλ��Ϣ
	@ResponseBody
	@RequestMapping(value = "/jobsupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Jobs jobs) {
		jobService.updateJobs(jobs);
		return "true";
	}
}
