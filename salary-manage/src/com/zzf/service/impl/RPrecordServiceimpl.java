package com.zzf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.po.RPrecord;
import com.zzf.service.RPrecordService;
import com.zzf.dao.RPrecordDao;

@Service
@Transactional
public class RPrecordServiceimpl implements RPrecordService {

	@Autowired
	private RPrecordDao rprecordDao;

	@Override
	public List<RPrecord> findAllRPrecord() {
		//
		return rprecordDao.selectRPrecord();
	}

	@Override
	public List<RPrecord> findRPrecordByWname(String wname) {
		//
		return rprecordDao.selectRPrecordByWname(wname);
	}

	@Override
	public List<RPrecord> findRPrecordByWno(String wno) {
		//
		return rprecordDao.selectRPrecordByWno(wno);
	}

	@Override
	public void addRPrecord(RPrecord rprecord) {

		rprecordDao.insertRPrecord(rprecord);

	}

	@Override
	public void deleteRPrecord(String[] rpnoArray) {

		for (int i = 0; i < rpnoArray.length; i++) {
			rprecordDao.deleteRPrecordByWno(rpnoArray[i]);
		}
	}

	@Override
	public void updateRPrecord(RPrecord rprecord) {
		//

		rprecordDao.updateRPrecord(rprecord);
	}

}
