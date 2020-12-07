package com.zzf.service;

import java.util.List;

import com.zzf.po.WSalary;

public interface WSalaryService {

	// 查询全部
	List<WSalary> findAllWSalary();

	// 关键字查询
	List<WSalary> findWSalaryByWname(String wname);

	// 按工号查询
	List<WSalary> findWSalaryByWno(String wno);

	// 按部门查询
	List<WSalary> findWSalaryByJDept(String jdept);

	// 添加
	void addWSalary(WSalary wsalary);

	// 删除
	void deleteWSalary(String[] wnoArray);

	// 修改
	void updateWSalary(WSalary wsalary);

	//清空表
	void truncateWsalary();
}
