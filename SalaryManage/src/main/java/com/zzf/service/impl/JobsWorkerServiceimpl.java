package com.zzf.service.impl;

import com.zzf.dao.JobsWorkersDao;
import com.zzf.pojo.JobsWorkers;
import com.zzf.pojo.Page;
import com.zzf.service.JobsWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class JobsWorkerServiceimpl implements JobsWorkerService {

	@Autowired
	private JobsWorkersDao jobsWorkers;

	//嵌套结果查询全部，员工职位模块
	@Override
	public Page<JobsWorkers> findJobsWithWorkers(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<JobsWorkers> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=jobsWorkers.selectJobsWithWorkersCounts();
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<JobsWorkers> datas=jobsWorkers.selectJobsWithWorkers(map);
		page.setDatas(datas);
		return page;
	}

	//嵌套结果查询，工资结算模块
	@Override
	public List<JobsWorkers> findJobsWithWorkers2() {
		return jobsWorkers.selectJobsWithWorkers2();
	}

	//按工号嵌套结果查询，员工职位模块
	@Override
	public Page<JobsWorkers> findJobsWithWorkersByWno(int currentPage,String wno) {
		HashMap<String, Object> map=new HashMap<>();
		Page<JobsWorkers> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=jobsWorkers.selectJobsWithWorkersByWnoCounts(wno);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		map.put("wno",wno);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<JobsWorkers> datas=jobsWorkers.selectJobsWithWorkersByWno(map);
		page.setDatas(datas);
		return page;
	}

	//按部门嵌套结果查询，员工职位模块
	@Override
	public Page<JobsWorkers> findJobsWithWorkersByJdept(int currentPage,String jdept) {
		HashMap<String, Object> map=new HashMap<>();
		Page<JobsWorkers> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=jobsWorkers.selectJobsWithWorkersByJdeptCounts(jdept);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		map.put("jdept",jdept);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<JobsWorkers> datas=jobsWorkers.selectJobsWithWorkersByJdept(map);
		page.setDatas(datas);
		return page;
	}
}
