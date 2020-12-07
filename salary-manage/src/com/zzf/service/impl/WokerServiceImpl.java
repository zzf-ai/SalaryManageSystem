package com.zzf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.WorkersDao;
import com.zzf.po.Workers;
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

	//�����Ų���Ա��
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

	//�������ؼ��ֲ���Ա��
	@Override
	public List<Workers> findWorkersByWname(String wname) {
		// TODO �Զ����ɵķ������
		return workersDao.selectWorkersByWname(wname);
	}

}
