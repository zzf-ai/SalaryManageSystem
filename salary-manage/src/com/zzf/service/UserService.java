package com.zzf.service;

import com.zzf.po.User;

import java.util.List;

public interface UserService {

	//查询全部
	public List<User> findAll();

	// 通过账号密码查询用户
	public User findUser(String usercode, String password);
	
	// 通过账号查询用户
	public User findUserCode(String usercode);

	// 通过工号查询用户
	public User findUserByWno(String wno);

	// 注册账户
	public void insertUser(User user);

	//删除用户
	public void deleteUser(String[] user_ids);

	//授权
	public void grant(String authority,String user_id);

	//修改密码
	public void changePassword(String password,String user_id);

	//修改用户名
	public void changeUserCode(String usercode,String user_id);
}
