package com.zzf.service.impl;

import java.util.HashMap;
import java.util.List;

import com.zzf.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.WorkersDao;
import com.zzf.pojo.Workers;
import com.zzf.service.WorkerService;

@Service
@Transactional
public class WokerServiceImpl implements WorkerService {

	@Autowired
	private WorkersDao workersDao;

	//��������Ա��
	@Override
	public List<Workers> findAllWorkers() {

		return workersDao.selectWorkers();
	}

	/**
	 * ��ҳ��ѯԱ��
	 * @param currentPage
	 * @return
	 */
	@Override
	public Page<Workers> findWorkersByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Workers> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=workersDao.selectCount();
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Workers> datas=workersDao.selectWorkersByPage(map);
		page.setDatas(datas);
		return page;
	}

	/**
	 * ���������ؼ��ֲ�ѯ
	 * @param currentPage
	 * @param wname
	 * @return
	 */
	@Override
	public Page<Workers> findWorkersByWnameByPage(int currentPage, String wname) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Workers> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=workersDao.selectCountsByWname(wname);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��,�ٶ��װWname
		map.put("wname",wname);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Workers> datas=workersDao.selectWorkersByWnameByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<Workers> findWorkersByWname(String wname) {
		// TODO �Զ����ɵķ������
		return workersDao.selectWorkersByWname(wname);
	}

	//��ѯ�ܼ�¼
	@Override
	public int findCount() {
		return workersDao.selectCount();
	}


	//�����Ų���Ա��
	@Override
	public Page<Workers> findWorkersByWnoByPage(int currentPage,String wno) {

		HashMap<String, Object> map=new HashMap<>();
		Page<Workers> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=workersDao.selectCountsByWno(wno);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��,�ٶ��װWname
		map.put("wno",wno);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<Workers> datas=workersDao.selectWorkersByWnoByPage(map);
		page.setDatas(datas);
		return page;
	}


	@Override
	public List<Workers> findWorkersByWno(String wno) {
		return workersDao.selectWorkersByWno(wno);
	}

	//���Ա��
	@Override
	public void addWorkers(Workers workers) {

		workersDao.insertWorkers(workers);
	}

	//ɾ��Ա��
	@Override
	public void deleteWorkers(String[] wnoArray) {

		for (int i = 0; i < wnoArray.length; i++) {
			workersDao.deleteWorkersByWno(wnoArray[i]);
		}
	}

	//����Ա��
	@Override
	public void updateWorkers(Workers workers) {

		workersDao.updateWorkers(workers);

	}


}
