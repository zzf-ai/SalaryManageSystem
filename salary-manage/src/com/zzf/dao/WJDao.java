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
    //����
    void insertWJ(Wj wj);
    // ����
    void updateWJ(Wj wj);
    // ɾ��
    void deleteWjById(Integer id);

    // ͨ�����Ų���
    Wj selectWJByWno(String wno);
}
