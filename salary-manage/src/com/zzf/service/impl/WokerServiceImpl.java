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

	//查找所有员工
	@Override
	public List<Workers> findAllWorkers() {

		return workersDao.selectWorkers();
	}

	//按工号查找员工
	@Override
	public List<Workers> findWorkersByWno(String wno) {

		return workersDao.selectWorkersByWno(wno);
	}

	//添加员工
	@Override
	public void addWorkers(Workers workers) {

		workersDao.insertWorkers(workers);
	}

	//删除员工
	@Override
	public void deleteWorkers(String[] wnoArray) {

		for (int i = 0; i < wnoArray.length; i++) {
			workersDao.deleteWorkersByWno(wnoArray[i]);
		}
	}

	//更新员工
	@Override
	public void updateWorkers(Workers workers) {

		workersDao.updateWorkers(workers);

	}

	//按姓名关键字查找员工
	@Override
	public List<Workers> findWorkersByWname(String wname) {
		// TODO 自动生成的方法存根
		return workersDao.selectWorkersByWname(wname);
	}

}
