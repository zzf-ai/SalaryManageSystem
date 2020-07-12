package com.zzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.po.RPrecord;

public interface RPrecordDao {

	// 通过工号查找
	List<RPrecord> selectRPrecordByWno(@Param(value = "wno") String wno);

	// 通过姓名关键字查找
	List<RPrecord> selectRPrecordByWname(@Param(value = "wname") String wname);

	// 查找全部
	List<RPrecord> selectRPrecord();

	// 插入
	void insertRPrecord(RPrecord rprecord);

	// 更新
	void updateRPrecord(RPrecord rprecord);

	// 删除
	void deleteRPrecordByWno(String rpno);
}
