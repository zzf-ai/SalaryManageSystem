package com.zzf.service.impl;

import com.zzf.dao.WJDao;
import com.zzf.po.Wj;
import com.zzf.service.WjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *
 *@author:zzf
 *@time:2020-10-26
 *
 */
@Service
@Transactional
public class WjServiceImpl implements WjService {

    @Autowired
    private WJDao wjDao;

    //���Ա��ְλ
    @Override
    public void addWj(Wj wj) {
        wjDao.insertWJ(wj);
    }

    //ɾ��Ա��ְλ
    @Override
    public void deleteWj(Integer[] id) {

        for (int i = 0; i < id.length; i++) {
            wjDao.deleteWjById(id[i]);
        }
    }

    //����Ա��ְλ
    @Override
    public void updateWj(Wj wj) {

        wjDao.updateWJ(wj);
    }

    @Override
    public List<Wj> findWJAll() {
        return wjDao.selectWJAll();
    }

    @Override
    public String[] findWnoOfWj() {
        return wjDao.selectWnoOfWj();
    }

    @Override
    public Wj findWJByWno(String wno) {
        return wjDao.selectWJByWno(wno);
    }

}
