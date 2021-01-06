package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import com.zzf.po.Page;
import org.apache.ibatis.annotations.Param;

import com.zzf.po.Workers;

public interface WorkersDao {

	// ͨ�����Ų���
	List<Workers> selectWorkersByWno(@Param(value = "wno") String wno);

	List<Workers> selectWorkersByWnoByPage(HashMap<String, Object> map);

	// ͨ�������ؼ��ֲ���
	List<Workers> selectWorkersByWname(@Param(value = "wname") String wname);

	// ����ȫ��
	List<Workers> selectWorkers();

	//��ҳ��ѯ
	List<Workers> selectWorkersByPage(HashMap<String, Object> map);

	//ģ����ҳ��ѯ
	List<Workers> selectWorkersByWnameByPage(HashMap<String, Object> map);

	//������
	int selectCountsByWno(@Param(value = "wno") String wno);

	//���ؼ���ģ����ѯ�ܼ�¼
	int selectCountsByWname(@Param(value = "wname")String wname);

	//��ѯ�ܼ�¼
	int selectCount();

	// ����
	void insertWorkers(Workers workers);

	// ����
	void updateWorkers(Workers workers);

	// ɾ��
	void deleteWorkersByWno(String wno);
}
