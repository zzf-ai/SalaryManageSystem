package com.zzf.service;

import java.util.List;

import com.zzf.pojo.JobsWorkers;
import com.zzf.pojo.Page;

public interface JobsWorkerService {

	// 查询全部，员工职位模块
	Page<JobsWorkers> findJobsWithWorkers(int currentPage);

	//查询全部，工资结算模块
	List<JobsWorkers> findJobsWithWorkers2();

	// 按工号查询
	Page<JobsWorkers> findJobsWithWorkersByWno(int currentPage, String wno);

	// 按部门查询
	Page<JobsWorkers> findJobsWithWorkersByJdept(int currentPage, String jdept);
}
