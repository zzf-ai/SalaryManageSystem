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

	@Override
	public List<Workers> findAllWorkers() {

		return workersDao.selectWorkers();
	}

	@Override
	public List<Workers> findWorkersByWno(String wno) {

		return workersDao.selectWorkersByWno(wno);
	}

	@Override
	public void addWorkers(Workers workers) {

		workersDao.insertWorkers(workers);
	}

	@Override
	public void deleteWorkers(String[] wnoArray) {

		for (int i = 0; i < wnoArray.length; i++) {
			workersDao.deleteWorkersByWno(wnoArray[i]);
		}
	}

	@Override
	public void updateWorkers(Workers workers) {

		workersDao.updateWorkers(workers);

	}

	@Override
	public List<Workers> findWorkersByWname(String wname) {
		// TODO 自动生成的方法存根
		return workersDao.selectWorkersByWname(wname);
	}

}
