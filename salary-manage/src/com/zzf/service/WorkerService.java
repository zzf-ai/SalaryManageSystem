package com.zzf.service;

import java.util.HashMap;
import java.util.List;

import com.zzf.po.Page;
import com.zzf.po.Workers;

public interface WorkerService {

	// ��ѯȫ��
	List<Workers> findAllWorkers();

	//��ҳ��ѯ
	Page<Workers> findWorkersByPage(int currentPage);

	Page<Workers> findWorkersByWnameByPage(int currentPage,String wname);

	//��ѯ�ܼ�¼
	int findCount();

	/*//ģ����ѯ�ܼ�¼
	int findCountsByWname();*/

	// �ؼ��ֲ�ѯ
	List<Workers> findWorkersByWname(String wname);

	// �����Ų�ѯ
	Page<Workers> findWorkersByWnoByPage(int currentPage,String wno);

	List<Workers> findWorkersByWno(String wno);

	// ���
	void addWorkers(Workers Workers);

	// ɾ��
	void deleteWorkers(String[] wnoArray);

	// �޸�
	void updateWorkers(Workers Workers);
}
