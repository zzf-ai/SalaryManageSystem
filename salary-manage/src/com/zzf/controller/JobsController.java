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
 * ��˾ְλ��Ϣ����ģ��
 *
 */
@Controller
public class JobsController {

	@Autowired
	private JobService jobService;


	// ��ѯȫ����Ϣ
	@RequestMapping("/jobslist.action")
	public String Jobslist(Model model,HttpSession session) {
		List<Jobs> list = jobService.findAllJobs();
		model.addAttribute("jobList", list);
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
	public String JobslistByWname(Model model, String jname,HttpSession session) {
		List<Jobs> list = jobService.findJobsByJname(jname.trim());
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}

	// �����Ų���
	@RequestMapping("/jobslistbyJdept.action")
	public String jobslistbyJdept(Model model, String jdept, HttpSession session) {
		List<Jobs> list = jobService.findJobsByJdept(jdept);
		model.addAttribute("jobList", list);
		return "jobs/jobslist";
	}


	//���ְλ
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


	//�޸�ְλ��Ϣ
	@RequestMapping(value = "/jobsupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Jobs jobs) {
		jobService.updateJobs(jobs);
		return "redirect:jobslist.action";
	}
}
