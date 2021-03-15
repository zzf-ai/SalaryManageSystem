package com.zzf.service;

import java.util.List;

import com.zzf.pojo.JobsWorkers;
import com.zzf.pojo.Page;

public interface JobsWorkerService {

	// ��ѯȫ����Ա��ְλģ��
	Page<JobsWorkers> findJobsWithWorkers(int currentPage);

	//��ѯȫ�������ʽ���ģ��
	List<JobsWorkers> findJobsWithWorkers2();

	// �����Ų�ѯ
	Page<JobsWorkers> findJobsWithWorkersByWno(int currentPage, String wno);

	// �����Ų�ѯ
	Page<JobsWorkers> findJobsWithWorkersByJdept(int currentPage, String jdept);
}
