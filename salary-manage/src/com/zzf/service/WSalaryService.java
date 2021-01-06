package com.zzf.service;

import java.util.List;

import com.zzf.po.Page;
import com.zzf.po.WSalary;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface WSalaryService {

	// 查询全部
	Page<WSalary> findAllWSalaryByPage(int currentPage);

	List<WSalary> findAllWSalary();

	// 关键字查询
	Page<WSalary> findWSalaryBySettleDateByPage(int currentPage,String settledate);

	List<WSalary> findWSalaryBySettleDate(String settledate);

	// 按工号查询
	Page<WSalary> findWSalaryByWno(int currentPage,String wno);

	List<WSalary> findselectMySalary(String wno);

	// 按部门查询
	Page<WSalary> findWSalaryByJDept(int currentPage,String jdept);

	//WSalary findWSalaryByWnoAndDate(WSalary wSalary);

	// 添加
	void addWSalary(WSalary wsalary);

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
