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

	//查找所有工资信息
	@Override
	public List<WSalary> findAllWSalary() {

		return wsalaryDao.selectWSalary();
	}

	//按姓名关键字查找工资信息
	@Override
	public List<WSalary> findWSalaryByWname(String wname) {

		return wsalaryDao.selectWSalaryByWname(wname);
	}

	//按工号查找工资信息
	@Override
	public List<WSalary> findWSalaryByWno(String wno) {

		return wsalaryDao.selectWSalaryByWno(wno);
	}

	//按部门查找工资信息
	@Override
	public List<WSalary> findWSalaryByJDept(String jdept) {
		return wsalaryDao.selectWSalaryByDept(jdept);
	}

	//添加工资信息
	@Override
	public void addWSalary(WSalary wsalary) {

		wsalaryDao.insertWSalary(wsalary);
	}

	//删除工资信息
	@Override
	public void deleteWSalary(String[] wnoArray) {

		for (int i = 0; i < wnoArray.length; i++) {
			wsalaryDao.deleteWSalaryByWno(wnoArray[i]);
		}
	}

	//修改工资信息
	@Override
	public void updateWSalary(WSalary wsalary) {

		wsalaryDao.updateWSalary(wsalary);
	}

	//清空
	@Override
	public void truncateWsalary() {
		wsalaryDao.truncate();
	}

}
