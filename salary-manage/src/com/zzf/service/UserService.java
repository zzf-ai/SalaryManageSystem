package com.zzf.service;

import com.zzf.po.User;

import java.util.List;

public interface UserService {

	//��ѯȫ��
	public List<User> findAll();

	// ͨ���˺������ѯ�û�
	public User findUser(String usercode, String password);
	
	// ͨ���˺Ų�ѯ�û�
	public User findUserCode(String usercode);

	// ͨ�����Ų�ѯ�û�
	public User findUserByWno(String wno);

	// ע���˻�
	public void insertUser(User user);

	//ɾ���û�
	public void deleteUser(String[] user_ids);

	//��Ȩ
	public void grant(String authority,String user_id);

	//�޸�����
	public void changePassword(String password,String user_id);

	//�޸��û���
	public void changeUserCode(String usercode,String user_id);
}
