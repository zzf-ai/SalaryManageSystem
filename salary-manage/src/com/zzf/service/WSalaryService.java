package com.zzf.service;

import java.util.List;

import com.zzf.po.Page;
import com.zzf.po.WSalary;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface WSalaryService {

	// ��ѯȫ��
	Page<WSalary> findAllWSalaryByPage(int currentPage);

	List<WSalary> findAllWSalary();

	// �ؼ��ֲ�ѯ
	Page<WSalary> findWSalaryBySettleDateByPage(int currentPage,String settledate);

	List<WSalary> findWSalaryBySettleDate(String settledate);

	// �����Ų�ѯ
	Page<WSalary> findWSalaryByWno(int currentPage,String wno);

	List<WSalary> findselectMySalary(String wno);

	// �����Ų�ѯ
	Page<WSalary> findWSalaryByJDept(int currentPage,String jdept);

	//WSalary findWSalaryByWnoAndDate(WSalary wSalary);

	// ���
	void addWSalary(WSalary wsalary);

	// ɾ��
	void deleteWSalary(int[] wsidArray);

	// ��ӡ
	HSSFWorkbook toExcel(List<WSalary> list);

	// ���Ź���
	void grantWSalary(int[] wsidArray);

	void grantWSalaryAll();

	//��ձ�
	void truncateWsalary();
}
