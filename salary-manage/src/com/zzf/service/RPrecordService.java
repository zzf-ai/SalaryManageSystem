package com.zzf.service;

import java.util.List;

import com.zzf.po.RPrecord;

public interface RPrecordService {

	// 查询全部
	List<RPrecord> findAllRPrecord();

	// 关键字查询
	List<RPrecord> findRPrecordByWname(String wname);

	// 按工号查询
	List<RPrecord> findRPrecordByWno(String wno);

	// 添加
	void addRPrecord(RPrecord rprecord);

	// 删除
	void deleteRPrecord(String[] rpnoArray);

	// 修改
	void updateRPrecord(RPrecord RPrecord);
}
