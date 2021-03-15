package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.pojo.WSalary;

public interface WSalaryDao {

	// 通过工号查找
	List<WSalary> selectWSalaryByWno(HashMap<String, Object> map);

	int selectWSalaryByWnoCounts(@Param(value = "wno") String wno);

	List<WSalary> selectMySalary(@Param(value = "wno") String wno);

	// 通过工资月份查找个人工资
	int selectMySalaryBySettleDatCount(@Param(value = "settledate") String settledate,@Param(value = "wno") String wno);

	List<WSalary> selectMySalaryBySettleDatByPage(HashMap<String, Object> map);


	// 通过工资月份查找全部工资
	List<WSalary> selectWSalaryBySettleDateByPage(HashMap<String, Object> map);

	List<WSalary> selectWSalaryBySettleDate(@Param(value = "settledate") String settledate);

	int selectWSalaryBySettleDateCounts(@Param(value = "settledate") String settledate);

	// 通过部门查找
	List<WSalary> selectWSalaryByDept(HashMap<String, Object> map);

	int selectWSalaryByDeptCounts(@Param(value = "jdept") String jdept);

	// 查找全部
	List<WSalary> selectWSalary();

	List<WSalary> selectWSalaryByPage(HashMap<String, Object> map);

	int selectWSalaryCounts();


	void deleteWSalaryByWnoAndDate(WSalary wSalary);

	// 插入
	void insertWSalary(WSalary wSalary);

	//批量插入
	void insertWSalaryBatch(List<WSalary> list);

	// 更新
	void updateWSalary(@Param(value = "wsid") int wsid);

	void updateWSalaryAll();

	// 删除
	void deleteWSalaryByWno(int wsid);

	//清空表
	void truncate();
}
