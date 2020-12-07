package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.Jobs;

public interface JobsDao {

	// ͨ��ְλ��Ų���
	List<Jobs> selectJobsByJno(@Param(value = "jno") String jno);

	// ͨ�����ƹؼ��ֲ���
	List<Jobs> selectJobsByJname(@Param(value = "jname") String jname);

	// ͨ�����ʲ���
	List<Jobs> selectJobsByJdept(@Param(value = "jdept") String jdept);

	// ����ȫ��
	List<Jobs> selectJobs();

	// ����
	void insertJobs(Jobs Jobs);

	// ����
	void updateJobs(Jobs Jobs);

	// ɾ��
	void deleteJobsByJno(String jno);

}
