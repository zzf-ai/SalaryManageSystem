package com.zzf.dao;

import org.apache.ibatis.annotations.Param;
import com.zzf.po.User;

public interface UserDao {

	// ͨ���˺������ѯ�û�
	public User findUser(@Param("usercode") String usercode, @Param("password") String password);

	// ͨ���˺Ų�ѯ�û�
	public User findUserCode(@Param("usercode") String usercode);

	// ע���û�
	public void insertUser(@Param("usercode") String usercode, @Param("password") String password);
}
