package com.zzf.pojo;

//�û�ʵ����
public class User {

	private Integer user_id;// id
	private String usercode;// �û��˺�
	private String password;// �û�����
	private String authority;// �û�Ȩ��
	private String wno;//�û�����

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}
}
