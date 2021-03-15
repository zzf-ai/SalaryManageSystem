package com.zzf.service.impl;

import com.zzf.Util.MD5Util;
import com.zzf.pojo.Page;
import com.zzf.pojo.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.UserDao;
import com.zzf.pojo.User;
import com.zzf.service.UserService;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// 注入UserDao
	@Autowired
	private UserDao userDao;


	@Override
	public Page<User> findUsersByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<User> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=userDao.selectUsersCount();
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
		List<User> datas=userDao.selectUsersByPage(map);
		page.setDatas(datas);
		return page;
	}

	// 通过账号密码查询用户
	@Override
	public User findUser(String usercode, String password) {
		User user = this.userDao.findUser(usercode, password);

		return user;
	}

	// 注册
	@Override
	public void insertUser(User user) {
		String password = MD5Util.getMD5String(user.getPassword());
		user.setPassword(password);
		System.out.println(password);
		userDao.insertUser(user);
	}

	//删除用户
	@Override
	public void deleteUser(String[] user_ids) {
		for (int i=0;i<user_ids.length;i++){
			userDao.deleteUsers(user_ids[i]);
		}
	}

	//授权
	@Override
	public void grant(String authority,String user_id) {

		userDao.grant(authority,user_id);
	}

	//修改密码
	@Override
	public void changePassword(String password, String user_id) {
		userDao.changePassword(password,user_id);
	}

	//修改账号
	@Override
	public void changeUserCode(String usercode, String user_id) {
		userDao.changeUserCode(usercode,user_id);
	}

	//查找账号
	@Override
	public User findUserCode(String usercode) {
		// TODO 自动生成的方法存根
		return userDao.findUserCode(usercode);
	}

	@Override
	public Page<User> findUsersByUserCodeByPage(int currentPage, String usercode) {
		HashMap<String, Object> map=new HashMap<>();
		Page<User> page=new Page<>();
		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=userDao.findUserCodeCount(usercode);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		//封装起始和每页显示页数
		map.put("usercode",usercode);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<User> datas=userDao.findUserCodeByPage(map);
		page.setDatas(datas);
		return page;
	}

	//按工号查找账号
	@Override
	public User findUserByWno(String wno) {
		return userDao.findUserByWno(wno);
	}

	@Override
	public Page<User> findUsersByWnoByPage(int currentPage, String wno) {
		HashMap<String, Object> map=new HashMap<>();
		Page<User> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=userDao.findUserByWnoCount(wno);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());

		//封装起始和每页显示页数
		map.put("wno",wno);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<User> datas=userDao.findUserByWnoByPage(map);
		page.setDatas(datas);
		return page;
	}

}
