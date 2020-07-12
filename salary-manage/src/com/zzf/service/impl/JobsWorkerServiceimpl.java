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

	@Override
	public List<JobsWorkers> findJobsWithWorkers() {
		return jobsWorkers.selectJobsWithWorkers();
	}

	@Override
	public List<JobsWorkers> findJobsWithWorkersByWno(String wno) {

		return jobsWorkers.selectJobsWithWorkersByWno(wno);
	}

}
