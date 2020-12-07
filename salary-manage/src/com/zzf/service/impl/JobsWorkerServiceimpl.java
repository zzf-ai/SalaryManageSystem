package com.zzf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzf.dao.JobsWorkersDao;
import com.zzf.po.JobsWorkers;
import com.zzf.service.JobsWorkerService;

@Service
public class JobsWorkerServiceimpl implements JobsWorkerService {

	@Autowired
	private JobsWorkersDao jobsWorkers;

	//Ƕ�׽����ѯȫ����Ա��ְλģ��
	@Override
	public List<JobsWorkers> findJobsWithWorkers() {
		return jobsWorkers.selectJobsWithWorkers();
	}

	//Ƕ�׽����ѯ�����ʽ���ģ��
	@Override
	public List<JobsWorkers> findJobsWithWorkers2() {
		return jobsWorkers.selectJobsWithWorkers2();
	}

	//������Ƕ�׽����ѯ��Ա��ְλģ��
	@Override
	public List<JobsWorkers> findJobsWithWorkersByWno(String wno) {

		return jobsWorkers.selectJobsWithWorkersByWno(wno);
	}

	//������Ƕ�׽����ѯ��Ա��ְλģ��
	@Override
	public List<JobsWorkers> findJobsWithWorkersByJdept(String jdept) {
		return jobsWorkers.selectJobsWithWorkersByJdept(jdept);
	}

}
