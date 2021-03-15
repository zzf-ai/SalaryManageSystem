package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import com.zzf.pojo.JobsWorkers;
import org.apache.ibatis.annotations.Param;

public interface JobsWorkersDao {

	// ����ȫ��
	List<JobsWorkers> selectJobsWithWorkers(HashMap<String, Object> map);

	//ȫ����¼��
	int selectJobsWithWorkersCounts();


	List<JobsWorkers> selectJobsWithWorkers2();

	// ͨ�����Ų���
	List<JobsWorkers> selectJobsWithWorkersByWno(HashMap<String, Object> map);

	int selectJobsWithWorkersByWnoCounts(@Param(value = "wno") String wno);

	// ͨ�����Ų���
	List<JobsWorkers> selectJobsWithWorkersByJdept(HashMap<String, Object> map);

	int selectJobsWithWorkersByJdeptCounts(@Param(value = "jdept") String jdept);
}
