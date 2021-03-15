package com.zzf.pojo;

//职位实体类
public class Jobs {

	private Integer jid;//id
	private String jno;// 职位编号
	private String jname;// 职位名称
	private float jsalary;// 基本工资
	private float jbonus;// 奖金
	private String jdept;//所属部门

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
