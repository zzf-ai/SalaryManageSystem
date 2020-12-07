package com.zzf.service;

import java.util.List;

import com.zzf.po.Jobs;

public interface JobService {
	// ��ѯȫ��ְλ��Ϣ
	List<Jobs> findAllJobs();

	// ��ְλ��Ų�ѯ
	List<Jobs> findJobsByJno(String jno);

	// ְλ���ؼ��ֲ�ѯ
	List<Jobs> findJobsByJname(String jname);

	// �����Ų�ѯ
	List<Jobs> findJobsByJdept(String jdept);

	// ���
	void addJobs(Jobs Jobs);

	// ɾ��
	void deleteJobs(String[] jnoArray);

	// �޸�
	void updateJobs(Jobs Jobs);
}
