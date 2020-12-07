package com.zzf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.UserDao;
import com.zzf.po.User;
import com.zzf.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// ע��UserDao
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.selectAll();
	}

	// ͨ���˺������ѯ�û�
	@Override
	public User findUser(String usercode, String password) {
		User user = this.userDao.findUser(usercode, password);

		return user;
	}

	// ע��
	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	//ɾ���û�
	@Override
	public void deleteUser(String[] user_ids) {
		for (int i=0;i<user_ids.length;i++){
			userDao.deleteUsers(user_ids[i]);
		}
	}

	//��Ȩ
	@Override
	public void grant(String authority,String user_id) {

		userDao.grant(authority,user_id);
	}

	//�޸�����
	@Override
	public void changePassword(String password, String user_id) {
		userDao.changePassword(password,user_id);
	}

	//�޸��˺�
	@Override
	public void changeUserCode(String usercode, String user_id) {
		userDao.changeUserCode(usercode,user_id);
	}

	//�����˺�
	@Override
	public User findUserCode(String usercode) {
		// TODO �Զ����ɵķ������
		return userDao.findUserCode(usercode);
	}

	//�����Ų����˺�
	@Override
	public User findUserByWno(String wno) {
		return userDao.findUserByWno(wno);
	}

}
