package com.zzf.service;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkerService {

	// 查询全部
	List<JobsWorkers> findJobsWithWorkers();

	// 按工号查询
	List<JobsWorkers> findJobsWithWorkersByWno(String wno);
}
