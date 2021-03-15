package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.pojo.Workers;

public interface WorkersDao {

	// 通过工号查找
	List<Workers> selectWorkersByWno(@Param(value = "wno") String wno);

	List<Workers> selectWorkersByWnoByPage(HashMap<String, Object> map);

	// 通过姓名关键字查找
	List<Workers> selectWorkersByWname(@Param(value = "wname") String wname);

	// 查找全部
	List<Workers> selectWorkers();

	//分页查询
	List<Workers> selectWorkersByPage(HashMap<String, Object> map);

	//模糊分页查询
	List<Workers> selectWorkersByWnameByPage(HashMap<String, Object> map);

	//按工号
	int selectCountsByWno(@Param(value = "wno") String wno);

	//按关键字模糊查询总记录
	int selectCountsByWname(@Param(value = "wname") String wname);

	//查询总记录
	int selectCount();

	// 插入
	void insertWorkers(Workers workers);

	// 更新
	void updateWorkers(Workers workers);

	// 删除
	void deleteWorkersByWno(String wno);
}
