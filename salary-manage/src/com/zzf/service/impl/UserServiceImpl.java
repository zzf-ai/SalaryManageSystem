package com.zzf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.UserDao;
import com.zzf.po.User;
import com.zzf.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// 注入UserDao
	@Autowired
	private UserDao userDao;

	// 通过账号密码查询用户
	@Override
	public User findUser(String usercode, String password) {
		User user = this.userDao.findUser(usercode, password);

		return user;
	}

	// 注册
	@Override
	public void insertUser(String usercode, String password) {
		this.userDao.insertUser(usercode, password);
	}

	@Override
	public User findUserCode(String usercode) {
		// TODO 自动生成的方法存根
		return this.userDao.findUserCode(usercode);
	}

}
