package com.zzf.service;

import java.util.List;

import com.zzf.po.RPrecord;

public interface RPrecordService {

	// ��ѯȫ��
	List<RPrecord> findAllRPrecord();

	// �ؼ��ֲ�ѯ
	List<RPrecord> findRPrecordByWname(String wname);

	// �����Ų�ѯ
	List<RPrecord> findRPrecordByWno(String wno);

	// ���
	void addRPrecord(RPrecord rprecord);

	// ɾ��
	void deleteRPrecord(String[] rpnoArray);

	// �޸�
	void updateRPrecord(RPrecord RPrecord);
}
