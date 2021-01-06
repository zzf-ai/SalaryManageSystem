package com.zzf.service.impl;

import java.util.HashMap;
import java.util.List;

import com.zzf.po.Page;
import com.zzf.po.Workers;
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

	//��ѯȫ��ְλ��Ϣ
	@Override
	public Page<Jobs> findJobsByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsDao.selectJobsCounts();
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Jobs> datas=jobsDao.selectJobsByPage(map);
		page.setDatas(datas);
		return page;
	}

	//��ְλ��Ų�ѯְλ��Ϣ
	@Override
	public Page<Jobs> findJobsByJnoByPage(int currentPage,String jno) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsDao.selectJobsCountsByJno(jno);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		map.put("jno",jno);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Jobs> datas=jobsDao.selectJobsByJnoByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<Jobs> findJobsByJno(String jno) {
		return jobsDao.selectJobsByJno(jno);
	}

	//��ְλ����ѯְλ��Ϣ
	@Override
	public Page<Jobs> findJobsByJname(int currentPage,String jname) {

		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsDao.selectJobsCountsByJname(jname);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		map.put("jname",jname);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Jobs> datas=jobsDao.selectJobsByJname(map);
		page.setDatas(datas);
		return page;
	}

	//�����Ų�ѯְλ��Ϣ
	@Override
	public Page<Jobs> findJobsByJdept(int currentPage,String jdept) {

		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsDao.selectJobsCountsByJdept(jdept);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		map.put("jdept",jdept);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Jobs> datas=jobsDao.selectJobsByJdept(map);
		page.setDatas(datas);
		return page;
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
