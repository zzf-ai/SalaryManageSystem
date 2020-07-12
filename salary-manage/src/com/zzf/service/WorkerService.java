package com.zzf.service;

import java.util.List;

import com.zzf.po.Workers;

public interface WorkerService {

	// ��ѯȫ��
	List<Workers> findAllWorkers();

	// �ؼ��ֲ�ѯ
	List<Workers> findWorkersByWname(String wname);

	// �����Ų�ѯ
	List<Workers> findWorkersByWno(String wno);

	// ���
	void addWorkers(Workers Workers);

	// ɾ��
	void deleteWorkers(String[] wnoArray);

	// �޸�
	void updateWorkers(Workers Workers);
}
