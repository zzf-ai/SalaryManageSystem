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
        //封装当前页数
        page.setCurrentPage(currentPage);

        //每页显示的数据
        int pageSize=5;
        page.setPageSize(pageSize);

        //封装总记录数
        int totalCount=workersDao.selectCount();
        page.setTotalCount(totalCount);

        //封装总页数
        double t=totalCount;
        Double num=Math.ceil(t/pageSize);//向上取整
        page.setTotalPage(num.intValue());
        return page;
    }*/
}
