package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.pojo.Jobs;

public interface JobsDao {

	// ͨ��ְλ��Ų���
	List<Jobs> selectJobsByJno(@Param(value = "jno") String jno);


	// ͨ�����ƹؼ��ֲ���
	List<Jobs> selectJobsByJname(HashMap<String, Object> map);

	// ͨ�����Ų���
	List<Jobs> selectJobsByJdept(HashMap<String, Object> map);

	// ����ȫ��
	List<Jobs> selectJobs();

	List<Jobs> selectJobsByPage(HashMap<String, Object> map);

	int selectJobsCounts();

	int selectJobsCountsByJdept(@Param(value = "jdept") String jdept);

	int selectJobsCountsByJname(@Param(value = "jname") String jname);


	// ����
	void insertJobs(Jobs Jobs);

	// ����
	void updateJobs(Jobs Jobs);

	// ɾ��
	void deleteJobsByJno(String jno);

}
