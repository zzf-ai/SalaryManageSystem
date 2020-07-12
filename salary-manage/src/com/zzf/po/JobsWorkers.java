package com.zzf.po;

import java.util.List;

//员工职位类，用于嵌套查询
public class JobsWorkers {

	private String wno;// 工号
	private String wname;// 姓名
	private List<Jobs> jobslist;// 职位List

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

	public List<Jobs> getJobslist() {
		return jobslist;
	}

	public void setJobslist(List<Jobs> jobslist) {
		this.jobslist = jobslist;
	}

}
