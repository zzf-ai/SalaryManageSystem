package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.RPrecord;

public interface RPrecordDao {

	// ͨ�����Ų���
	List<RPrecord> selectRPrecordByWno(@Param(value = "wno") String wno);

	// ͨ�������ؼ��ֲ���
	List<RPrecord> selectRPrecordByWname(@Param(value = "wname") String wname);

	// ����ȫ��
	List<RPrecord> selectRPrecord();

	// ����
	void insertRPrecord(RPrecord rprecord);

	// ����
	void updateRPrecord(RPrecord rprecord);

	// ɾ��
	void deleteRPrecordByWno(String rpno);
}
