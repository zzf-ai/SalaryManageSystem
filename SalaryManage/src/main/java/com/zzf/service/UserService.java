package com.zzf.service;

import com.zzf.pojo.Page;
import com.zzf.pojo.User;

import java.util.List;

public interface UserService {

	//查询全部
	Page<User> findUsersByPage(int currentPage);

	// 通过账号密码查询用户
	User findUser(String usercode, String password);
	
	// 通过账号查询用户
	User findUserCode(String usercode);

	//分页查询
	Page<User> findUsersByUserCodeByPage(int currentPage,String usercode);

	// 通过工号查询用户
	User findUserByWno(String wno);

	//分页查询
	Page<User> findUsersByWnoByPage(int currentPage,String wno);

	// 注册账户
	void insertUser(User user);

	//删除用户
	void deleteUser(String[] user_ids);

	//授权
	void grant(String authority, String user_id);

	//修改密码
	void changePassword(String password, String user_id);

	//修改用户名
	void changeUserCode(String usercode, String user_id);
}
