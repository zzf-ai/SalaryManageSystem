package com.zzf.Util;

import com.zzf.dao.WorkersDao;
import com.zzf.po.Page;
import com.zzf.po.Workers;
import com.zzf.service.WorkerService;
import org.apache.poi.ss.formula.functions.T;

/*
 *
 *@author:zzf
 *@time:2020-12-14
 *
 */
public class PackPage {
    /*public static Page<T> packPage(Page<T> page, int currentPage, WorkersDao workersDao){
        //��װ��ǰҳ��
        page.setCurrentPage(currentPage);

        //ÿҳ��ʾ������
        int pageSize=5;
        page.setPageSize(pageSize);

        //��װ�ܼ�¼��
        int totalCount=workersDao.selectCount();
        page.setTotalCount(totalCount);

        //��װ��ҳ��
        double t=totalCount;
        Double num=Math.ceil(t/pageSize);//����ȡ��
        page.setTotalPage(num.intValue());
        return page;
    }*/
}
