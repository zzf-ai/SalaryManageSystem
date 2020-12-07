package com.zzf.service;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkerService {

	// 查询全部，员工职位模块
	List<JobsWorkers> findJobsWithWorkers();

	//查询全部，工资结算模块
	List<JobsWorkers> findJobsWithWorkers2();

	// 按工号查询
	List<JobsWorkers> findJobsWithWorkersByWno(String wno);

	// 按部门查询
	List<JobsWorkers> findJobsWithWorkersByJdept(String jdept);
}
