package com.zzf.dao;

import com.zzf.pojo.Workers;
import org.apache.ibatis.annotations.Param;
import com.zzf.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserDao {

	//��ѯȫ���û�

	List<User> selectUsersByPage(HashMap<String, Object> map);

	int selectUsersCount();

	// ͨ���˺������ѯ�û�
	User findUser(@Param("usercode") String usercode, @Param("password") String password);

	// ͨ���˺Ų�ѯ�û�
	User findUserCode(@Param("usercode") String usercode);

	List<User> findUserCodeByPage(HashMap<String, Object> map);

	int findUserCodeCount(@Param("usercode") String usercode);

	// ͨ�����Ų�ѯ�û�
	User findUserByWno(@Param("wno") String wno);

	int findUserByWnoCount(@Param("wno") String wno);

	List<User> findUserByWnoByPage(HashMap<String, Object> map);

	// ע���û�
	void insertUser(User user);

	//ɾ���û�
	void deleteUsers(String user_id);

	//��Ȩ
	void grant(@Param("authority") String authority, @Param("user_id") String user_id);

	//�޸�����
	void changePassword(@Param("password") String password, @Param("user_id") String user_id);

	//�޸��û���
	void changeUserCode(@Param("usercode") String password, @Param("user_id") String user_id);
}
