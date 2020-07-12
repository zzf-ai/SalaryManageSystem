package com.zzf.po;

//工资信息实体类
public class WSalary {

	private String wno;// 工号
	private String wname;// 姓名
	private String jno;// 职位编号
	private String jname;// 职位
	private String jsalary;// 基本工资
	private String bonus;// 奖罚金
	private String total;// 总工资

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

	public String getJsalary() {
		return jsalary;
	}

	public void setJsalary(String jsalary) {
		this.jsalary = jsalary;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
