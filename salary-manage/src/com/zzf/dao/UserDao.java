package com.zzf.dao;

import org.apache.ibatis.annotations.Param;
import com.zzf.po.User;

public interface UserDao {

	// 通过账号密码查询用户
	public User findUser(@Param("usercode") String usercode, @Param("password") String password);

	// 通过账号查询用户
	public User findUserCode(@Param("usercode") String usercode);

	// 注册用户
	public void insertUser(@Param("usercode") String usercode, @Param("password") String password);
}
