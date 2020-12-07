package com.zzf.service;

import java.util.List;

import com.zzf.po.JobsWorkers;

public interface JobsWorkerService {

	// ��ѯȫ����Ա��ְλģ��
	List<JobsWorkers> findJobsWithWorkers();

	//��ѯȫ�������ʽ���ģ��
	List<JobsWorkers> findJobsWithWorkers2();

	// �����Ų�ѯ
	List<JobsWorkers> findJobsWithWorkersByWno(String wno);

	// �����Ų�ѯ
	List<JobsWorkers> findJobsWithWorkersByJdept(String jdept);
}
