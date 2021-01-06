package com.zzf.po;

import java.util.List;

/*
 *
 *@author:zzf
 *@time:2020-12-14
 *��ҳʵ����
 */
public class Page<T> {
    private int currentPage;//��ǰҳ��
    private int pageSize;//ÿҳ��ʾ�ļ�¼��
    private int totalCount;//�ܼ�¼��
    private int totalPage;//��ҳ��
    private List<T> datas;//ÿҳ��ʾ������

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
