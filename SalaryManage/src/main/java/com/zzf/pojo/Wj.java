package com.zzf.pojo;

//员工职位表实体类，用于增加、修改、删除
public class Wj {
    private Integer id;
    private String wno;
    private String jno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWno() {
        return wno;
    }

    public void setWno(String wno) {
        this.wno = wno;
    }

    public String getJno() {
        return jno;
    }

    public void setJno(String jno) {
        this.jno = jno;
    }
}
