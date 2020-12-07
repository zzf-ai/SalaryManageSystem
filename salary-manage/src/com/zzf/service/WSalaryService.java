package com.zzf.service;

import java.util.List;

import com.zzf.po.WSalary;

public interface WSalaryService {

	// ��ѯȫ��
	List<WSalary> findAllWSalary();

	// �ؼ��ֲ�ѯ
	List<WSalary> findWSalaryByWname(String wname);

	// �����Ų�ѯ
	List<WSalary> findWSalaryByWno(String wno);

	// �����Ų�ѯ
	List<WSalary> findWSalaryByJDept(String jdept);

	// ���
	void addWSalary(WSalary wsalary);

	// ɾ��
	void deleteWSalary(String[] wnoArray);

	// �޸�
	void updateWSalary(WSalary wsalary);

	//��ձ�
	void truncateWsalary();
}
