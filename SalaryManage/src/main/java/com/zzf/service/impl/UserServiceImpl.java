package com.zzf.service.impl;

import com.zzf.Util.MD5Util;
import com.zzf.pojo.Page;
import com.zzf.pojo.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.UserDao;
import com.zzf.pojo.User;
import com.zzf.service.UserService;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// ע��UserDao
	@Autowired
	private UserDao userDao;


	@Override
	public Page<User> findUsersByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<User> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=userDao.selectUsersCount();
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<User> datas=userDao.selectUsersByPage(map);
		page.setDatas(datas);
		return page;
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
		String password = MD5Util.getMD5String(user.getPassword());
		user.setPassword(password);
		System.out.println(password);
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

	@Override
	public Page<User> findUsersByUserCodeByPage(int currentPage, String usercode) {
		HashMap<String, Object> map=new HashMap<>();
		Page<User> page=new Page<>();
		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=userDao.findUserCodeCount(usercode);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("usercode",usercode);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<User> datas=userDao.findUserCodeByPage(map);
		page.setDatas(datas);
		return page;
	}

	//�����Ų����˺�
	@Override
	public User findUserByWno(String wno) {
		return userDao.findUserByWno(wno);
	}

	@Override
	public Page<User> findUsersByWnoByPage(int currentPage, String wno) {
		HashMap<String, Object> map=new HashMap<>();
		Page<User> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=userDao.findUserByWnoCount(wno);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());

		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("wno",wno);
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<User> datas=userDao.findUserByWnoByPage(map);
		page.setDatas(datas);
		return page;
	}

}
