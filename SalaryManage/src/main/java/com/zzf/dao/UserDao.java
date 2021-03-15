package com.zzf.dao;

import com.zzf.pojo.Workers;
import org.apache.ibatis.annotations.Param;
import com.zzf.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserDao {

	//查询全部用户

	List<User> selectUsersByPage(HashMap<String, Object> map);

	int selectUsersCount();

	// 通过账号密码查询用户
	User findUser(@Param("usercode") String usercode, @Param("password") String password);

	// 通过账号查询用户
	User findUserCode(@Param("usercode") String usercode);

	List<User> findUserCodeByPage(HashMap<String, Object> map);

	int findUserCodeCount(@Param("usercode") String usercode);

	// 通过工号查询用户
	User findUserByWno(@Param("wno") String wno);

	int findUserByWnoCount(@Param("wno") String wno);

	List<User> findUserByWnoByPage(HashMap<String, Object> map);

	// 注册用户
	void insertUser(User user);

	//删除用户
	void deleteUsers(String user_id);

	//授权
	void grant(@Param("authority") String authority, @Param("user_id") String user_id);

	//修改密码
	void changePassword(@Param("password") String password, @Param("user_id") String user_id);

	//修改用户名
	void changeUserCode(@Param("usercode") String password, @Param("user_id") String user_id);
}
