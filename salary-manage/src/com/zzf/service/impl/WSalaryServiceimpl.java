package com.zzf.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.zzf.po.Jobs;
import com.zzf.po.Page;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.WSalaryDao;
import com.zzf.po.WSalary;
import com.zzf.service.WSalaryService;

@Service
@Transactional
public class WSalaryServiceimpl implements WSalaryService {

	@Autowired
	private WSalaryDao wsalaryDao;

	@Override
	public Page<WSalary> findAllWSalaryByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=wsalaryDao.selectWSalaryCounts();
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());


		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<WSalary> datas=wsalaryDao.selectWSalaryByPage(map);
		page.setDatas(datas);
		return page;
	}

	//�������й�����Ϣ
	@Override
	public List<WSalary> findAllWSalary() {
		return wsalaryDao.selectWSalary();
	}

	//�������ؼ��ֲ��ҹ�����Ϣ
	@Override
	public Page<WSalary> findWSalaryBySettleDateByPage(int currentPage, String settledate) {

		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=wsalaryDao.selectWSalaryBySettleDateCounts(settledate);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());


		map.put("settledate",settledate);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<WSalary> datas=wsalaryDao.selectWSalaryBySettleDateByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<WSalary> findWSalaryBySettleDate(String settledate) {
		return wsalaryDao.selectWSalaryBySettleDate(settledate);
	}

	//�����Ų��ҹ�����Ϣ
	@Override
	public Page<WSalary> findWSalaryByWno(int currentPage,String wno) {

		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=wsalaryDao.selectWSalaryByWnoCounts(wno);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());


		map.put("wno",wno);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<WSalary> datas=wsalaryDao.selectWSalaryByWno(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<WSalary> findselectMySalary(String wno) {
		return wsalaryDao.selectMySalary(wno);
	}

	//�����Ų��ҹ�����Ϣ
	@Override
	public Page<WSalary> findWSalaryByJDept(int currentPage,String jdept) {
		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//��װ��ǰҳ��
		page.setCurrentPage(currentPage);

		//ÿҳ��ʾ������
		int pageSize=5;
		page.setPageSize(pageSize);

		//��װ�ܼ�¼��
		int totalCount=wsalaryDao.selectWSalaryByDeptCounts(jdept);
		page.setTotalCount(totalCount);

		//��װ��ҳ��
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//����ȡ��
		page.setTotalPage(num.intValue());


		map.put("jdept",jdept);
		//��װ��ʼ��ÿҳ��ʾҳ��
		map.put("start",(currentPage-1)*pageSize);
		map.put("size",page.getPageSize());

		//��װÿҳ��ʾ������
		List<WSalary> datas=wsalaryDao.selectWSalaryByDept(map);
		page.setDatas(datas);
		return page;
	}

	//��ӹ�����Ϣ
	@Override
	public void addWSalary(WSalary wsalary) {
		wsalaryDao.deleteWSalaryByWnoAndDate(wsalary);
		wsalaryDao.insertWSalary(wsalary);
	}

	//ɾ��������Ϣ
	@Override
	public void deleteWSalary(int[] wsidArray) {
		for (int i = 0; i < wsidArray.length; i++) {
			wsalaryDao.deleteWSalaryByWno(wsidArray[i]);
		}
	}

	@Override
	public HSSFWorkbook toExcel(List<WSalary> list) {
		//����excel�ļ�
		HSSFWorkbook wbook=new HSSFWorkbook();
		//����sheetҳ
		HSSFSheet sheet=wbook.createSheet("������Ϣ����");
		//����������
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("����");
		titleRow.createCell(1).setCellValue("����");
		titleRow.createCell(2).setCellValue("ְλ���");
		titleRow.createCell(3).setCellValue("ְλ����");
		titleRow.createCell(4).setCellValue("��������");
		titleRow.createCell(5).setCellValue("��������");
		titleRow.createCell(6).setCellValue("����");
		titleRow.createCell(7).setCellValue("�ܹ���");
		titleRow.createCell(8).setCellValue("�����·�");
		titleRow.createCell(9).setCellValue("�Ƿ��ѷ���");
		titleRow.createCell(10).setCellValue("��������");

		//�����ݷ���excel
		for (WSalary wsalary:list) {
			HSSFRow dataRow=sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(wsalary.getWno());
			dataRow.createCell(1).setCellValue(wsalary.getWname());
			dataRow.createCell(2).setCellValue(wsalary.getJno());
			dataRow.createCell(3).setCellValue(wsalary.getJname());
			dataRow.createCell(4).setCellValue(wsalary.getJdept());
			dataRow.createCell(5).setCellValue(wsalary.getJsalary());
			dataRow.createCell(6).setCellValue(wsalary.getJbonus());
			dataRow.createCell(7).setCellValue(wsalary.getTotal());
			dataRow.createCell(8).setCellValue(wsalary.getSettledate());
			dataRow.createCell(9).setCellValue(wsalary.getIsgrant());
			dataRow.createCell(10).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(wsalary.getGrantdate()));

		}
		return wbook;
	}

	//�޸Ĺ�����Ϣ
	/*@Override
	public void updateWSalary(WSalary wsalary) {

		wsalaryDao.updateWSalary(wsalary);
	}*/

	@Override
	public void grantWSalary(int[] wsidArray) {
		for (int i = 0; i < wsidArray.length; i++) {
			wsalaryDao.updateWSalary(wsidArray[i]);
		}
	}

	@Override
	public void grantWSalaryAll() {
		/*List<WSalary> list = wsalaryDao.selectWSalary();
		for (WSalary wsalary:list) {
			System.out.println(wsalary.getIsgrant());
			if(wsalary.getIsgrant().equals("��"))
				wsalaryDao.updateWSalaryAll();
		}*/
		wsalaryDao.updateWSalaryAll();
	}

	//���
	@Override
	public void truncateWsalary() {
		wsalaryDao.truncate();
	}

}
