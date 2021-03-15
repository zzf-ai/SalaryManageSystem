package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.pojo.Jobs;

public interface JobsDao {

	// 通过职位编号查找
	List<Jobs> selectJobsByJno(@Param(value = "jno") String jno);


	// 通过名称关键字查找
	List<Jobs> selectJobsByJname(HashMap<String, Object> map);

	// 通过部门查找
	List<Jobs> selectJobsByJdept(HashMap<String, Object> map);

	// 查找全部
	List<Jobs> selectJobs();

	List<Jobs> selectJobsByPage(HashMap<String, Object> map);

	int selectJobsCounts();

	int selectJobsCountsByJdept(@Param(value = "jdept") String jdept);

	int selectJobsCountsByJname(@Param(value = "jname") String jname);


	// 插入
	void insertJobs(Jobs Jobs);

	// 更新
	void updateJobs(Jobs Jobs);

	// 删除
	void deleteJobsByJno(String jno);

}
