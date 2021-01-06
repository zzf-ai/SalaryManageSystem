package com.zzf.dao;

import com.zzf.po.Jobs;
import com.zzf.po.JobsWorkers;
import com.zzf.po.Wj;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *
 *@author:zzf
 *@time:2020-10-26
 *
 */

public interface WJDao {
    //插入
    void insertWJ(Wj wj);
    // 更新
    void updateWJ(Wj wj);
    // 删除
    void deleteWjById(Integer id);

    // 全部查找
    List<Wj> selectWJAll();

    // 查找工号
    String[] selectWnoOfWj();

    // 通过工号查找
    Wj selectWJByWno(String wno);
}
