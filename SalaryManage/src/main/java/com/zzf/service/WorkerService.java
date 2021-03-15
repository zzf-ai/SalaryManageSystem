package com.zzf.service;

import java.util.List;

import com.zzf.pojo.Page;
import com.zzf.pojo.Workers;

public interface WorkerService {

	// 查询全部
	List<Workers> findAllWorkers();

	//分页查询
	Page<Workers> findWorkersByPage(int currentPage);

	Page<Workers> findWorkersByWnameByPage(int currentPage, String wname);

	//查询总记录
	int findCount();

	// 关键字查询
	List<Workers> findWorkersByWname(String wname);

	// 按工号查询
	Page<Workers> findWorkersByWnoByPage(int currentPage, String wno);

	List<Workers> findWorkersByWno(String wno);

	// 添加
	void addWorkers(Workers Workers);

	// 删除
	void deleteWorkers(String[] wnoArray);

	// 修改
	void updateWorkers(Workers Workers);
}
