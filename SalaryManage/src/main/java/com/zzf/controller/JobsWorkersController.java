package com.zzf.controller;

import com.zzf.pojo.*;
import com.zzf.service.JobService;
import com.zzf.service.JobsWorkerService;
import com.zzf.service.WjService;
import com.zzf.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
	public String JobsWorkerslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model) {
		Page<JobsWorkers> page = jobsWorkersService.findJobsWithWorkers(currentPage);
		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobsWorkersService.findJobsWithWorkers(currentPage-1);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = jobsWorkersService.findJobsWithWorkers(page.getTotalPage());
		}
		model.addAttribute("JobsWorkersList", page);
		List<Jobs> jobs=jobService.findAllJobs();
		model.addAttribute("Jobs", jobs);
		List<String> wnos=wjService.findWnoOfWj();
		List<Workers> workers=workerService.findAllWorkers();
		List<Workers> workers2=workerService.findAllWorkers();
		for(int i=0;i<wnos.size();i++){
			for (int j=0;j<workers.size();j++){
				if(workers.get(j).getWno().equals(wnos.get(i))){
					workers.remove(j);
				}
			}
		}
		model.addAttribute("Workers", workers);
		model.addAttribute("Workers2", workers2);
		model.addAttribute("flag3",0);
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
	public String JobsWorkerslistByWno(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wno) {
		Page<JobsWorkers> page = jobsWorkersService.findJobsWithWorkersByWno(currentPage,wno);
		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobsWorkersService.findJobsWithWorkersByJdept(currentPage-1,wno);
		}
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = jobsWorkersService.findJobsWithWorkersByWno(page.getTotalPage(),wno);
		}
		model.addAttribute("JobsWorkersList", page);
		List<Jobs> jobs=jobService.findAllJobs();
		model.addAttribute("Jobs", jobs);
		List<String> wnos=wjService.findWnoOfWj();
		List<Workers> workers=workerService.findAllWorkers();
		List<Workers> workers2=workerService.findAllWorkers();
		for(int i=0;i<wnos.size();i++){
			for (int j=0;j<workers.size();j++){
				if(workers.get(j).getWno().equals(wnos.get(i))){
					workers.remove(j);
				}
			}
		}
		model.addAttribute("Workers", workers);
		model.addAttribute("Workers2", workers2);
		model.addAttribute("flag3",1);
		model.addAttribute("wno",wno);
		return "JobsWorkers/jobsWorkerslist";
	}

	// 按部门查询
	@RequestMapping("/JobsWorkerslistByJdept.action")
	public String JobsWorkerslistByJdept(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jdept) {
		Page<JobsWorkers> page = jobsWorkersService.findJobsWithWorkersByJdept(currentPage,jdept);
		if(page.getDatas().size()==0&&currentPage!=1){
			page = jobsWorkersService.findJobsWithWorkersByJdept(currentPage-1,jdept);
		}
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = jobsWorkersService.findJobsWithWorkersByJdept(page.getTotalPage(),jdept);
		}
		model.addAttribute("JobsWorkersList", page);
		List<Jobs> jobs=jobService.findAllJobs();
		model.addAttribute("Jobs", jobs);
		List<String> wnos=wjService.findWnoOfWj();
		List<Workers> workers=workerService.findAllWorkers();
		List<Workers> workers2=workerService.findAllWorkers();
		for(int i=0;i<wnos.size();i++){
			for (int j=0;j<workers.size();j++){
				if(workers.get(j).getWno().equals(wnos.get(i))){
					workers.remove(j);
				}
			}
		}
		model.addAttribute("Workers", workers);
		model.addAttribute("Workers2", workers2);
		model.addAttribute("flag3",2);
		model.addAttribute("jdept",jdept);
		return "JobsWorkers/jobsWorkerslist";
	}

	//添加
	@ResponseBody
	@RequestMapping(value = "/insertWj.action", method = RequestMethod.POST)
	public String insertWj(Wj wj) {
		String s=wj.getWno().trim().split(",")[0];
		String str=wj.getJno().trim().split(",")[0];
		System.out.println(s);
		System.out.println(str);
		wj.setWno(s);
		wj.setJno(str);
		Wj wj1 = wjService.findWJByWno(s);
		if(wj1==null) {
			wjService.addWj(wj);
			return "true";
		}
		else {
			return "false";
		}
	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "/wjdelete.action", method = RequestMethod.POST)
	public String JobsDelete(Integer[] id) {
		wjService.deleteWj(id);
		return "true";
	}

	//修改
	@ResponseBody
	@RequestMapping(value = "/wjupdate.action", method = RequestMethod.POST)
	public String JobsUpdate(Wj wj) {
		String str1=wj.getWno().trim().split(",")[0];
		String str2=wj.getJno().trim().split(",")[0];
		wj.setWno(str1);
		wj.setJno(str2);
		Wj wj1 = wjService.findWJByWno(str1);
		if(wj1!=null) {
			wjService.updateWj(wj);
			return "true";
		}
		else {
			return "false";
		}
	}
}
