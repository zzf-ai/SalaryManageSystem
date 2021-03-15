package com.zzf.service.impl;

import java.util.HashMap;
import java.util.List;

import com.zzf.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.WorkersDao;
import com.zzf.pojo.Workers;
import com.zzf.service.WorkerService;

@Service
@Transactional
public class WokerServiceImpl implements WorkerService {

	@Autowired
	private WorkersDao workersDao;

	//查找所有员工
	@Override
	public List<Workers> findAllWorkers() {

		return workersDao.selectWorkers();
	}

	/**
	 * 分页查询员工
	 * @param currentPage
	 * @return
	 */
	@Override
	public Page<Workers> findWorkersByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Workers> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=workersDao.selectCount();
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
		List<Workers> datas=workersDao.selectWorkersByPage(map);
		page.setDatas(datas);
		return page;
	}

	/**
	 * 根据姓名关键字查询
	 * @param currentPage
	 * @param wname
	 * @return
	 */
	@Override
	public Page<Workers> findWorkersByWnameByPage(int currentPage, String wname) {
		HashMap<String, Object> map=new HashMap<>();
		Page<Workers> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=workersDao.selectCountsByWname(wname);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		//封装起始和每页显示页数,再多封装Wname
		map.put("wname",wname);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<Workers> datas=workersDao.selectWorkersByWnameByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<Workers> findWorkersByWname(String wname) {
		// TODO 自动生成的方法存根
		return workersDao.selectWorkersByWname(wname);
	}

	//查询总记录
	@Override
	public int findCount() {
		return workersDao.selectCount();
	}


	//按工号查找员工
	@Override
	public Page<Workers> findWorkersByWnoByPage(int currentPage,String wno) {

		HashMap<String, Object> map=new HashMap<>();
		Page<Workers> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=workersDao.selectCountsByWno(wno);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		//封装起始和每页显示页数,再多封装Wname
		map.put("wno",wno);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<Workers> datas=workersDao.selectWorkersByWnoByPage(map);
		page.setDatas(datas);
		return page;
	}


	@Override
	public List<Workers> findWorkersByWno(String wno) {
		return workersDao.selectWorkersByWno(wno);
	}

	//添加员工
	@Override
	public void addWorkers(Workers workers) {

		workersDao.insertWorkers(workers);
	}

	//删除员工
	@Override
	public void deleteWorkers(String[] wnoArray) {

		for (int i = 0; i < wnoArray.length; i++) {
			workersDao.deleteWorkersByWno(wnoArray[i]);
		}
	}

	//更新员工
	@Override
	public void updateWorkers(Workers workers) {

		workersDao.updateWorkers(workers);

	}


}
