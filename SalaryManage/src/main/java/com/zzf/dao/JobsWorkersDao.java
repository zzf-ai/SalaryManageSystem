package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import com.zzf.pojo.JobsWorkers;
import org.apache.ibatis.annotations.Param;

public interface JobsWorkersDao {

	// 查找全部
	List<JobsWorkers> selectJobsWithWorkers(HashMap<String, Object> map);

	//全部记录数
	int selectJobsWithWorkersCounts();


	List<JobsWorkers> selectJobsWithWorkers2();

	// 通过工号查找
	List<JobsWorkers> selectJobsWithWorkersByWno(HashMap<String, Object> map);

	int selectJobsWithWorkersByWnoCounts(@Param(value = "wno") String wno);

	// 通过部门查找
	List<JobsWorkers> selectJobsWithWorkersByJdept(HashMap<String, Object> map);

	int selectJobsWithWorkersByJdeptCounts(@Param(value = "jdept") String jdept);
}
