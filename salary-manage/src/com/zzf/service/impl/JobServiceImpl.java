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

	//��ѯȫ��ְλ��Ϣ
	@Override
	public List<Jobs> findAllJobs() {

		return jobsDao.selectJobs();
	}

	//��ְλ��Ų�ѯְλ��Ϣ
	@Override
	public List<Jobs> findJobsByJno(String jno) {
		return jobsDao.selectJobsByJno(jno);
	}

	//��ְλ����ѯְλ��Ϣ
	@Override
	public List<Jobs> findJobsByJname(String jname) {

		return jobsDao.selectJobsByJname(jname);
	}

	//�����Ų�ѯְλ��Ϣ
	@Override
	public List<Jobs> findJobsByJdept(String jdept) {

		return jobsDao.selectJobsByJdept(jdept);
	}

	//���ְλ��Ϣ
	@Override
	public void addJobs(Jobs Jobs) {

		jobsDao.insertJobs(Jobs);
	}

	//ɾ��ְλ��Ϣ
	@Override
	public void deleteJobs(String[] jnoArray) {
		for (int i = 0; i < jnoArray.length; i++) {
			jobsDao.deleteJobsByJno(jnoArray[i]);
		}

	}

	//�޸�ְλ��Ϣ
	@Override
	public void updateJobs(Jobs Jobs) {
		jobsDao.updateJobs(Jobs);

	}

}
