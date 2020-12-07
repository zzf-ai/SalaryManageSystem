package com.zzf.po;

//工资信息实体类
public class WSalary {

	private Integer wsid;//id
	private String wno;// 工号
	private String wname;// 姓名
	private String jno;// 职位编号
	private String jname;// 职位
	private String jdept;//所属部门
	private float jsalary;// 基本工资
	private float jbonus;// 奖金
	private float total;// 总工资or应发工资


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
