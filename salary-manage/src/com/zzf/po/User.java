package com.zzf.po;

import java.io.Serializable;

//账号实体类
public class User implements Serializable {

	private static final long serivalVersionUID = 1L;
	private Integer user_id;// id
	private String user_code;// 用户账号
	private String user_password;// 用户密码

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

}
