package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.Workers;

public interface WorkersDao {

	// ͨ�����Ų���
	List<Workers> selectWorkersByWno(@Param(value = "wno") String wno);

	// ͨ�������ؼ��ֲ���
	List<Workers> selectWorkersByWname(@Param(value = "wname") String wname);

	// ����ȫ��
	List<Workers> selectWorkers();

	// ����
	void insertWorkers(Workers workers);

	// ����
	void updateWorkers(Workers workers);

	// ɾ��
	void deleteWorkersByWno(String wno);
}
