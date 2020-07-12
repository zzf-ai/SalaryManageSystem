package com.zzf.service;

import com.zzf.po.User;

public interface UserService {

	// 通过账号密码查询用户
	public User findUser(String usercode, String password);
	
	// 通过账号查询用户
	public User findUserCode(String usercode);

	// 注册账户
	public void insertUser(String usercode, String password);
}
