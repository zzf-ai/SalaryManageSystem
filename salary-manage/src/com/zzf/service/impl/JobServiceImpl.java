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

	//查询全部职位信息
	@Override
	public List<Jobs> findAllJobs() {

		return jobsDao.selectJobs();
	}

	//按职位编号查询职位信息
	@Override
	public List<Jobs> findJobsByJno(String jno) {
		return jobsDao.selectJobsByJno(jno);
	}

	//按职位名查询职位信息
	@Override
	public List<Jobs> findJobsByJname(String jname) {

		return jobsDao.selectJobsByJname(jname);
	}

	//按部门查询职位信息
	@Override
	public List<Jobs> findJobsByJdept(String jdept) {

		return jobsDao.selectJobsByJdept(jdept);
	}

	//添加职位信息
	@Override
	public void addJobs(Jobs Jobs) {

		jobsDao.insertJobs(Jobs);
	}

	//删除职位信息
	@Override
	public void deleteJobs(String[] jnoArray) {
		for (int i = 0; i < jnoArray.length; i++) {
			jobsDao.deleteJobsByJno(jnoArray[i]);
		}

	}

	//修改职位信息
	@Override
	public void updateJobs(Jobs Jobs) {
		jobsDao.updateJobs(Jobs);

	}

}
