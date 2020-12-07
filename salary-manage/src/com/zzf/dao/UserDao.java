package com.zzf.dao;

import org.apache.ibatis.annotations.Param;
import com.zzf.po.User;

import java.util.List;

public interface UserDao {

	//��ѯȫ���û�
	public List<User> selectAll();

	// ͨ���˺������ѯ�û�
	public User findUser(@Param("usercode") String usercode, @Param("password") String password);

	// ͨ���˺Ų�ѯ�û�
	public User findUserCode(@Param("usercode") String usercode);

	// ͨ�����Ų�ѯ�û�
	public User findUserByWno(@Param("wno") String wno);

	// ע���û�
	public void insertUser(User user);

	//ɾ���û�
	public void deleteUsers(String user_id);

	//��Ȩ
	public void grant(@Param("authority") String authority,@Param("user_id") String user_id);

	//�޸�����
	public void changePassword(@Param("password") String password,@Param("user_id") String user_id);

	//�޸��û���
	public void changeUserCode(@Param("usercode") String password,@Param("user_id") String user_id);
}
