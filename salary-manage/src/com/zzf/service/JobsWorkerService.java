package com.zzf.service;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkerService {

	// ��ѯȫ��
	List<JobsWorkers> findJobsWithWorkers();

	// �����Ų�ѯ
	List<JobsWorkers> findJobsWithWorkersByWno(String wno);
}
