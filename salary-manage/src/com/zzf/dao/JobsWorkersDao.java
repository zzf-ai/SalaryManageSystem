package com.zzf.dao;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkersDao {

	// ����ȫ��
	List<JobsWorkers> selectJobsWithWorkers();

	// ͨ�����Ų���
	List<JobsWorkers> selectJobsWithWorkersByWno(String wno);
}
