package com.zzf.service;

import java.util.List;

import com.zzf.po.Jobs;

public interface JobService {
	// 查询全部职位信息
	List<Jobs> findAllJobs();

	// 关键字查询
	List<Jobs> findJobsByJname(String jname);

	// 按工资查询
	List<Jobs> findJobsByJsalary(float jsalary);

	// 添加
	void addJobs(Jobs Jobs);

	// 删除
	void deleteJobs(String[] jnoArray);

	// 修改
	void updateJobs(Jobs Jobs);
}
