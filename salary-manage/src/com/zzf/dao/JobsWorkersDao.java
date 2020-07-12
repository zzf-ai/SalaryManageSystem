package com.zzf.dao;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkersDao {

	// 查找全部
	List<JobsWorkers> selectJobsWithWorkers();

	// 通过工号查找
	List<JobsWorkers> selectJobsWithWorkersByWno(String wno);
}
