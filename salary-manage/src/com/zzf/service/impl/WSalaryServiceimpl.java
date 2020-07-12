package com.zzf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.WSalaryDao;
import com.zzf.po.WSalary;
import com.zzf.service.WSalaryService;

@Service
@Transactional
public class WSalaryServiceimpl implements WSalaryService {

	@Autowired
	private WSalaryDao wsalaryDao;

	@Override
	public List<WSalary> findAllWSalary() {

		return wsalaryDao.selectWSalary();
	}

	@Override
	public List<WSalary> findWSalaryByWname(String wname) {

		return wsalaryDao.selectWSalaryByWname(wname);
	}

	@Override
	public List<WSalary> findWSalaryByWno(String wno) {

		return wsalaryDao.selectWSalaryByWno(wno);
	}

	@Override
	public void addWSalary(WSalary wsalary) {

		wsalaryDao.insertWSalary(wsalary);
	}

	@Override
	public void deleteWSalary(String[] wnoArray) {

		for (int i = 0; i < wnoArray.length; i++) {
			wsalaryDao.deleteWSalaryByWno(wnoArray[i]);
		}
	}

	@Override
	public void updateWSalary(WSalary wsalary) {

		wsalaryDao.updateWSalary(wsalary);
	}

}
