package com.zzf.service;

import com.zzf.po.Wj;

import java.util.List;

/*
 *
 *@author:zzf
 *@time:2020-10-26
 *
 */
public interface WjService {

    // ���
    void addWj(Wj wj);

    // ɾ��
    void deleteWj(Integer[] id);

    // �޸�
    void updateWj(Wj wj);

    // ͨ�����Ų���
    Wj findWJByWno(String wno);
}
