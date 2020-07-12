package com.zzf.service;

import java.util.List;

import com.zzf.po.Jobs;

public interface JobService {
	// ��ѯȫ��ְλ��Ϣ
	List<Jobs> findAllJobs();

	// �ؼ��ֲ�ѯ
	List<Jobs> findJobsByJname(String jname);

	// �����ʲ�ѯ
	List<Jobs> findJobsByJsalary(float jsalary);

	// ���
	void addJobs(Jobs Jobs);

	// ɾ��
	void deleteJobs(String[] jnoArray);

	// �޸�
	void updateJobs(Jobs Jobs);
}
