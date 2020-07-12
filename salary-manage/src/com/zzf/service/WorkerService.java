package com.zzf.service;

import java.util.List;

import com.zzf.po.Workers;

public interface WorkerService {

	// 查询全部
	List<Workers> findAllWorkers();

	// 关键字查询
	List<Workers> findWorkersByWname(String wname);

	// 按工号查询
	List<Workers> findWorkersByWno(String wno);

	// 添加
	void addWorkers(Workers Workers);

	// 删除
	void deleteWorkers(String[] wnoArray);

	// 修改
	void updateWorkers(Workers Workers);
}
