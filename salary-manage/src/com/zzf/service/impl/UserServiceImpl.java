package com.zzf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.UserDao;
import com.zzf.po.User;
import com.zzf.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// 注入UserDao
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.selectAll();
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

	//按工号查找账号
	@Override
	public User findUserByWno(String wno) {
		return userDao.findUserByWno(wno);
	}

}
