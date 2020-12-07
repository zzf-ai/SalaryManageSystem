package com.zzf.dao;

import org.apache.ibatis.annotations.Param;
import com.zzf.po.User;

import java.util.List;

public interface UserDao {

	//查询全部用户
	public List<User> selectAll();

	// 通过账号密码查询用户
	public User findUser(@Param("usercode") String usercode, @Param("password") String password);

	// 通过账号查询用户
	public User findUserCode(@Param("usercode") String usercode);

	// 通过工号查询用户
	public User findUserByWno(@Param("wno") String wno);

	// 注册用户
	public void insertUser(User user);

	//删除用户
	public void deleteUsers(String user_id);

	//授权
	public void grant(@Param("authority") String authority,@Param("user_id") String user_id);

	//修改密码
	public void changePassword(@Param("password") String password,@Param("user_id") String user_id);

	//修改用户名
	public void changeUserCode(@Param("usercode") String password,@Param("user_id") String user_id);
}
