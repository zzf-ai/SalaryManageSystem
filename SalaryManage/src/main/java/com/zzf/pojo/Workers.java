package com.zzf.pojo;

//Ա��ʵ����
public class Workers {

	private Integer wid;//id
	private String wno;// ����
	private String wname;// Ա������
	private String wsex;// Ա���Ա�
	private String wnative;// Ա������
	private String wphone;// Ա���ֻ���

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
