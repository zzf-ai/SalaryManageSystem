package com.zzf.service;

import java.util.List;

import com.zzf.pojo.Page;
import com.zzf.pojo.WSalary;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface WSalaryService {

	// 查询全部
	Page<WSalary> findAllWSalaryByPage(int currentPage);

	List<WSalary> findAllWSalary();

	// 月份查询
	Page<WSalary> findWSalaryBySettleDateByPage(int currentPage, String settledate);

	Page<WSalary> findMySalaryBySettleDateByPage(int currentPage, String settledate,String wno);


	List<WSalary> findWSalaryBySettleDate(String settledate);

	// 按工号查询
	Page<WSalary> findWSalaryByWno(int currentPage, String wno);

	List<WSalary> findselectMySalary(String wno);


	// 按部门查询
	Page<WSalary> findWSalaryByJDept(int currentPage, String jdept);


	// 添加
	void addWSalary(WSalary wsalary);

	//批量添加
	void addWSalaryBatch(List<WSalary> wSalaryList);

	// 删除
	void deleteWSalary(int[] wsidArray);

	// 打印
	HSSFWorkbook toExcel(List<WSalary> list);

	// 发放工资
	void grantWSalary(int[] wsidArray);

	void grantWSalaryAll();

	//清空表
	void truncateWsalary();
}
