package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.WSalary;

public interface WSalaryDao {

	// 通过工号查找
	List<WSalary> selectWSalaryByWno(@Param(value = "wno") String wno);

	// 通过姓名关键字查找
	List<WSalary> selectWSalaryByWname(@Param(value = "wname") String wname);

	// 通过部门查找
	List<WSalary> selectWSalaryByDept(@Param(value = "jdept") String jdept);

	// 查找全部
	List<WSalary> selectWSalary();

	// 插入
	void insertWSalary(WSalary wSalary);

	// 更新
	void updateWSalary(WSalary wSalary);

	// 删除
	void deleteWSalaryByWno(String wno);

	//清空表
	void truncate();
}
