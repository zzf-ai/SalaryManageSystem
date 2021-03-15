package com.zzf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzf.pojo.WSalary;

public interface WSalaryDao {

	// ͨ�����Ų���
	List<WSalary> selectWSalaryByWno(HashMap<String, Object> map);

	int selectWSalaryByWnoCounts(@Param(value = "wno") String wno);

	List<WSalary> selectMySalary(@Param(value = "wno") String wno);

	// ͨ�������·ݲ��Ҹ��˹���
	int selectMySalaryBySettleDatCount(@Param(value = "settledate") String settledate,@Param(value = "wno") String wno);

	List<WSalary> selectMySalaryBySettleDatByPage(HashMap<String, Object> map);


	// ͨ�������·ݲ���ȫ������
	List<WSalary> selectWSalaryBySettleDateByPage(HashMap<String, Object> map);

	List<WSalary> selectWSalaryBySettleDate(@Param(value = "settledate") String settledate);

	int selectWSalaryBySettleDateCounts(@Param(value = "settledate") String settledate);

	// ͨ�����Ų���
	List<WSalary> selectWSalaryByDept(HashMap<String, Object> map);

	int selectWSalaryByDeptCounts(@Param(value = "jdept") String jdept);

	// ����ȫ��
	List<WSalary> selectWSalary();

	List<WSalary> selectWSalaryByPage(HashMap<String, Object> map);

	int selectWSalaryCounts();


	void deleteWSalaryByWnoAndDate(WSalary wSalary);

	// ����
	void insertWSalary(WSalary wSalary);

	//��������
	void insertWSalaryBatch(List<WSalary> list);

	// ����
	void updateWSalary(@Param(value = "wsid") int wsid);

	void updateWSalaryAll();

	// ɾ��
	void deleteWSalaryByWno(int wsid);

	//��ձ�
	void truncate();
}
