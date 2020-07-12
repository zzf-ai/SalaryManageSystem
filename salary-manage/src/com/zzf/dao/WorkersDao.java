package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.Workers;

public interface WorkersDao {

	// 通过工号查找
	List<Workers> selectWorkersByWno(@Param(value = "wno") String wno);

	// 通过姓名关键字查找
	List<Workers> selectWorkersByWname(@Param(value = "wname") String wname);

	// 查找全部
	List<Workers> selectWorkers();

	// 插入
	void insertWorkers(Workers workers);

	// 更新
	void updateWorkers(Workers workers);

	// 删除
	void deleteWorkersByWno(String wno);
}
