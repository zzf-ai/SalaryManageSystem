package com.zzf.pojo;

//员工实体类
public class Workers {

	private Integer wid;//id
	private String wno;// 工号
	private String wname;// 员工姓名
	private String wsex;// 员工性别
	private String wnative;// 员工籍贯
	private String wphone;// 员工手机号

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getWsex() {
		return wsex;
	}

	public void setWsex(String wsex) {
		this.wsex = wsex;
	}

	public String getWnative() {
		return wnative;
	}

	public void setWnative(String wnative) {
		this.wnative = wnative;
	}

	public String getWphone() {
		return wphone;
	}

	public void setWphone(String wphone) {
		this.wphone = wphone;
	}

	@Override
	public String toString() {
		return "Workers{" +
				"wid=" + wid +
				", wno='" + wno + '\'' +
				", wname='" + wname + '\'' +
				", wsex='" + wsex + '\'' +
				", wnative='" + wnative + '\'' +
				", wphone='" + wphone + '\'' +
				'}';
	}
}
