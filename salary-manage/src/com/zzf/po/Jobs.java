package com.zzf.po;

//ְλʵ����
public class Jobs {

	private Integer jid;//id
	private String jno;// ְλ���
	private String jname;// ְλ����
	private float jsalary;// ��������
	private float jbonus;// ����
	private String jdept;//��������

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
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

	public String getJdept() {
		return jdept;
	}

	public void setJdept(String jdept) {
		this.jdept = jdept;
	}
}
