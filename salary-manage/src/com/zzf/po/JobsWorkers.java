package com.zzf.po;

import java.util.List;

//Ա��ְλ�࣬����Ƕ�ײ�ѯ
public class JobsWorkers {

	private String wno;// ����
	private String wname;// ����
	private List<Jobs> jobslist;// ְλList
	private Integer id;//��¼���

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
