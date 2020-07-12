package com.zzf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.JobsDao;
import com.zzf.po.Jobs;
import com.zzf.service.JobService;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobsDao jobsDao;

	@Override
	public List<Jobs> findAllJobs() {

		return jobsDao.selectJobs();
	}

	@Override
	public List<Jobs> findJobsByJname(String jname) {

		return jobsDao.selectJobsByJname(jname);
	}

	@Override
	public List<Jobs> findJobsByJsalary(float jsalary) {

		return jobsDao.selectJobsByJsalary(jsalary);
	}

	@Override
	public void addJobs(Jobs Jobs) {

		jobsDao.insertJobs(Jobs);
	}

	@Override
	public void deleteJobs(String[] jnoArray) {
		for (int i = 0; i < jnoArray.length; i++) {
			jobsDao.deleteJobsByJno(jnoArray[i]);
		}

	}

	@Override
	public void updateJobs(Jobs Jobs) {
		jobsDao.updateJobs(Jobs);

	}

}
