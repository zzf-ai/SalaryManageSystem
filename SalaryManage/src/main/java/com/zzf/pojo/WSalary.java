package com.zzf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//������Ϣʵ����
public class WSalary {

	private Integer wsid;//id
	private String wno;// ����
	private String wname;// ����
	private String jno;// ְλ���
	private String jname;// ְλ
	private String jdept;//��������
	private float jsalary;// ��������
	private float jbonus;// ����
	private String settledate;//��������
	private float total;// �ܹ���orӦ������
	private String isgrant;//�Ƿ��ѷ���

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date grantdate;//��������

	public String getSettledate() {
		return settledate;
	}

	public void setSettledate(String settledate) {
		this.settledate = settledate;
	}

	public Date getGrantdate() {
		return grantdate;
	}

	public void setGrantdate(Date grantdate) {
		this.grantdate = grantdate;
	}

	public String getIsgrant() {
		return isgrant;
	}

	public void setIsgrant(String isgrant) {
		this.isgrant = isgrant;
	}

	public Integer getWsid() {
		return wsid;
	}

	public void setWsid(Integer wsid) {
		this.wsid = wsid;
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

	public String getJno() {
		return jno;
	}

	public void setJno(String jno) {
		this.jno = jno;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getJdept() {
		return jdept;
	}

	public void setJdept(String jdept) {
		this.jdept = jdept;
	}

	public float getJsalary() {
		return jsalary;
	}

	public void setJsalary(float jsalary) {
		this.jsalary = jsalary;
	}

	public float getJbonus() {
		return jbonus;
	}

	public void setJbonus(float jbonus) {
		this.jbonus = jbonus;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
