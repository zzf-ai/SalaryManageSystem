package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.Jobs;

public interface JobsDao {

	// 通过名称关键字查找
	List<Jobs> selectJobsByJname(@Param(value = "jname") String jname);

	// 通过工资查找
	List<Jobs> selectJobsByJsalary(@Param(value = "jsalary") float jsalary);

	// 查找全部
	List<Jobs> selectJobs();

	// 插入
	void insertJobs(Jobs Jobs);

	// 更新
	void updateJobs(Jobs Jobs);

	// 删除
	void deleteJobsByJno(String jno);

}
