package com.zzf.po;

//奖惩记录持久化类
public class RPrecord {

	private String rpno;// 记录编号
	private String wno;// 工号
	private String wname;// 姓名
	private String wjob;// 职位
	private float rewardOrpunish;// 奖罚金，正数为奖励，负数为惩罚

	public String getRpno() {
		return rpno;
	}

	public void setRpno(String rpno) {
		this.rpno = rpno;
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

	public String getWjob() {
		return wjob;
	}

	public void setWjob(String wjob) {
		this.wjob = wjob;
	}

	public float getRewardOrpunish() {
		return rewardOrpunish;
	}

	public void setRewardOrpunish(float rewardOrpunish) {
		this.rewardOrpunish = rewardOrpunish;
	}

}
