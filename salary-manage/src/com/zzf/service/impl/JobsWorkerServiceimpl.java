package com.zzf.service.impl;

import com.zzf.dao.JobsWorkersDao;
import com.zzf.po.JobsWorkers;
import com.zzf.po.Page;
import com.zzf.service.JobsWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class JobsWorkerServiceimpl implements JobsWorkerService {

	@Autowired
	private JobsWorkersDao jobsWorkers;

	//Ƕ�׽����ѯȫ����Ա��ְλģ��
	@Override
	public Page<JobsWorkers> findJobsWithWorkers(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<JobsWorkers> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsWorkers.selectJobsWithWorkersCounts();
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<JobsWorkers> datas=jobsWorkers.selectJobsWithWorkers(map);
		page.setDatas(datas);
		return page;
	}

	//Ƕ�׽����ѯ�����ʽ���ģ��
	@Override
	public List<JobsWorkers> findJobsWithWorkers2() {
		return jobsWorkers.selectJobsWithWorkers2();
	}

	//������Ƕ�׽����ѯ��Ա��ְλģ��
	@Override
	public Page<JobsWorkers> findJobsWithWorkersByWno(int currentPage,String wno) {
		HashMap<String, Object> map=new HashMap<>();
		Page<JobsWorkers> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsWorkers.selectJobsWithWorkersByWnoCounts(wno);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		map.put("wno",wno);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<JobsWorkers> datas=jobsWorkers.selectJobsWithWorkersByWno(map);
		page.setDatas(datas);
		return page;
	}

	//������Ƕ�׽����ѯ��Ա��ְλģ��
	@Override
	public Page<JobsWorkers> findJobsWithWorkersByJdept(int currentPage,String jdept) {
		HashMap<String, Object> map=new HashMap<>();
		Page<JobsWorkers> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=jobsWorkers.selectJobsWithWorkersByJdeptCounts(jdept);
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
		List<JobsWorkers> datas=jobsWorkers.selectJobsWithWorkersByJdept(map);
		page.setDatas(datas);
		return page;
	}
}
