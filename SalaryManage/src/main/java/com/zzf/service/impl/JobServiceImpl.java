package com.zzf.service.impl;

import java.util.HashMap;
import java.util.List;

import com.zzf.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.JobsDao;
import com.zzf.pojo.Jobs;
import com.zzf.service.JobService;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobsDao jobsDao;

	@Override
	public List<Jobs> findAllJobs() {
		return jobsDao.selectJobs();
	}

	//查询全部职位信息
	@Override
	public Page<Jobs> findJobsByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=jobsDao.selectJobsCounts();
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<Jobs> datas=jobsDao.selectJobsByPage(map);
		page.setDatas(datas);
		return page;
	}


	@Override
	public List<Jobs> findJobsByJno(String jno) {
		return jobsDao.selectJobsByJno(jno);
	}

	//按职位名查询职位信息
	@Override
	public Page<Jobs> findJobsByJname(int currentPage,String jname) {

		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=jobsDao.selectJobsCountsByJname(jname);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		map.put("jname",jname);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<Jobs> datas=jobsDao.selectJobsByJname(map);
		page.setDatas(datas);
		return page;
	}

	//按部门查询职位信息
	@Override
	public Page<Jobs> findJobsByJdept(int currentPage,String jdept) {

		HashMap<String, Object> map=new HashMap<>();
		Page<Jobs> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=jobsDao.selectJobsCountsByJdept(jdept);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		map.put("jdept",jdept);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<Jobs> datas=jobsDao.selectJobsByJdept(map);
		page.setDatas(datas);
		return page;
	}

	//添加职位信息
	@Override
	public void addJobs(Jobs Jobs) {

		jobsDao.insertJobs(Jobs);
	}

	//删除职位信息
	@Override
	public void deleteJobs(String[] jnoArray) {
		for (int i = 0; i < jnoArray.length; i++) {
			jobsDao.deleteJobsByJno(jnoArray[i]);
		}

	}

	//修改职位信息
	@Override
	public void updateJobs(Jobs Jobs) {
		jobsDao.updateJobs(Jobs);

	}

}
