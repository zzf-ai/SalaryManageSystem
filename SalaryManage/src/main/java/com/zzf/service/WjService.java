package com.zzf.service;

import com.zzf.pojo.Wj;

import java.util.List;

/*
 *
 *@author:zzf
 *@time:2020-10-26
 *
 */
public interface WjService {

    // 添加
    void addWj(Wj wj);

    // 删除
    void deleteWj(Integer[] id);

    // 修改
    void updateWj(Wj wj);



    //查找工号
    List<String> findWnoOfWj();

    // 通过工号查找
    Wj findWJByWno(String wno);
}
