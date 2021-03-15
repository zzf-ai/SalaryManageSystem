package com.zzf.dao;

import com.zzf.pojo.Wj;

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

    // ȫ������
    List<Wj> selectWJAll();

    // ���ҹ���
    List<String> selectWnoOfWj();

    // ͨ�����Ų���
    Wj selectWJByWno(String wno);
}
