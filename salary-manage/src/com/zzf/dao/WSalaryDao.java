package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.WSalary;

public interface WSalaryDao {

	// ͨ�����Ų���
	List<WSalary> selectWSalaryByWno(@Param(value = "wno") String wno);

	// ͨ�������ؼ��ֲ���
	List<WSalary> selectWSalaryByWname(@Param(value = "wname") String wname);

	// ����ȫ��
	List<WSalary> selectWSalary();

	// ����
	void insertWSalary(WSalary wSalary);

	// ����
	void updateWSalary(WSalary wSalary);

	// ɾ��
	void deleteWSalaryByWno(String wno);
}
