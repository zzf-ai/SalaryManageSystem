package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.Jobs;

public interface JobsDao {

	// ͨ�����ƹؼ��ֲ���
	List<Jobs> selectJobsByJname(@Param(value = "jname") String jname);

	// ͨ�����ʲ���
	List<Jobs> selectJobsByJsalary(@Param(value = "jsalary") float jsalary);

	// ����ȫ��
	List<Jobs> selectJobs();

	// ����
	void insertJobs(Jobs Jobs);

	// ����
	void updateJobs(Jobs Jobs);

	// ɾ��
	void deleteJobsByJno(String jno);

}
