package com.zzf.service;

import com.zzf.pojo.Page;
import com.zzf.pojo.User;

import java.util.List;

public interface UserService {

	//��ѯȫ��
	Page<User> findUsersByPage(int currentPage);

	// ͨ���˺������ѯ�û�
	User findUser(String usercode, String password);
	
	// ͨ���˺Ų�ѯ�û�
	User findUserCode(String usercode);

	//��ҳ��ѯ
	Page<User> findUsersByUserCodeByPage(int currentPage,String usercode);

	// ͨ�����Ų�ѯ�û�
	User findUserByWno(String wno);

	//��ҳ��ѯ
	Page<User> findUsersByWnoByPage(int currentPage,String wno);

	// ע���˻�
	void insertUser(User user);

	//ɾ���û�
	void deleteUser(String[] user_ids);

	//��Ȩ
	void grant(String authority, String user_id);

	//�޸�����
	void changePassword(String password, String user_id);

	//�޸��û���
	void changeUserCode(String usercode, String user_id);
}
