package com.zzf.service;

import com.zzf.po.User;

public interface UserService {

	// ͨ���˺������ѯ�û�
	public User findUser(String usercode, String password);
	
	// ͨ���˺Ų�ѯ�û�
	public User findUserCode(String usercode);

	// ע���˻�
	public void insertUser(String usercode, String password);
}
