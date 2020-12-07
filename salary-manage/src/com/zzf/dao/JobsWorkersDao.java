package com.zzf.dao;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkersDao {

	// ����ȫ��
	List<JobsWorkers> selectJobsWithWorkers();

	List<JobsWorkers> selectJobsWithWorkers2();

	// ͨ�����Ų���
	List<JobsWorkers> selectJobsWithWorkersByWno(String wno);

	// ͨ�����Ų���
	List<JobsWorkers> selectJobsWithWorkersByJdept(String jdept);
}
