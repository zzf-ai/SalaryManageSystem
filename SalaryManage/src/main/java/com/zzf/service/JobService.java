package com.zzf.service;

import java.util.List;

import com.zzf.pojo.Jobs;
import com.zzf.pojo.Page;

public interface JobService {
	// 查询全部职位信息
	List<Jobs> findAllJobs();

	Page<Jobs> findJobsByPage(int currentPage);


	List<Jobs> findJobsByJno(String jno);

	// 职位名关键字查询
	Page<Jobs> findJobsByJname(int currentPage, String jname);

	// 按部门查询
	Page<Jobs> findJobsByJdept(int currentPage, String jdept);

	// 添加
	void addJobs(Jobs Jobs);

	// 删除
	void deleteJobs(String[] jnoArray);

	// 修改
	void updateJobs(Jobs Jobs);
}
