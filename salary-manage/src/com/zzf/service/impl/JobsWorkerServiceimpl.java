package com.zzf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzf.dao.JobsWorkersDao;
import com.zzf.po.JobsWorkers;
import com.zzf.service.JobsWorkerService;

@Service
public class JobsWorkerServiceimpl implements JobsWorkerService {

	@Autowired
	private JobsWorkersDao jobsWorkers;

	//嵌套结果查询全部，员工职位模块
	@Override
	public List<JobsWorkers> findJobsWithWorkers() {
		return jobsWorkers.selectJobsWithWorkers();
	}

	//嵌套结果查询，工资结算模块
	@Override
	public List<JobsWorkers> findJobsWithWorkers2() {
		return jobsWorkers.selectJobsWithWorkers2();
	}

	//按工号嵌套结果查询，员工职位模块
	@Override
	public List<JobsWorkers> findJobsWithWorkersByWno(String wno) {

		return jobsWorkers.selectJobsWithWorkersByWno(wno);
	}

	//按部门嵌套结果查询，员工职位模块
	@Override
	public List<JobsWorkers> findJobsWithWorkersByJdept(String jdept) {
		return jobsWorkers.selectJobsWithWorkersByJdept(jdept);
	}

}
