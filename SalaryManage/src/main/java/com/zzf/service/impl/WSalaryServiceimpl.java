package com.zzf.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.zzf.pojo.Page;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzf.dao.WSalaryDao;
import com.zzf.pojo.WSalary;
import com.zzf.service.WSalaryService;

@Service
@Transactional
public class WSalaryServiceimpl implements WSalaryService {

	@Autowired
	private WSalaryDao wsalaryDao;

	/**
	 * 查询所有工资信息
	 * @param currentPage
	 * @return
	 */
	@Override
	public Page<WSalary> findAllWSalaryByPage(int currentPage) {
		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=wsalaryDao.selectWSalaryCounts();
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());


		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<WSalary> datas=wsalaryDao.selectWSalaryByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<WSalary> findAllWSalary() {
		return wsalaryDao.selectWSalary();
	}

	//按工资月份查找
	@Override
	public Page<WSalary> findWSalaryBySettleDateByPage(int currentPage, String settledate) {

		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=wsalaryDao.selectWSalaryBySettleDateCounts(settledate);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());


		map.put("settledate",settledate);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<WSalary> datas=wsalaryDao.selectWSalaryBySettleDateByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public Page<WSalary> findMySalaryBySettleDateByPage(int currentPage, String wno, String settledate) {
		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=wsalaryDao.selectMySalaryBySettleDatCount(settledate,wno);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());


		map.put("settledate",settledate);
		map.put("wno",wno);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<WSalary> datas=wsalaryDao.selectMySalaryBySettleDatByPage(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<WSalary> findWSalaryBySettleDate(String settledate) {
		return wsalaryDao.selectWSalaryBySettleDate(settledate);
	}

	//按工号查找工资信息
	@Override
	public Page<WSalary> findWSalaryByWno(int currentPage,String wno) {

		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=wsalaryDao.selectWSalaryByWnoCounts(wno);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());


		map.put("wno",wno);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<WSalary> datas=wsalaryDao.selectWSalaryByWno(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public List<WSalary> findselectMySalary(String wno) {
		return wsalaryDao.selectMySalary(wno);
	}


	//按部门查找工资信息
	@Override
	public Page<WSalary> findWSalaryByJDept(int currentPage,String jdept) {
		HashMap<String, Object> map=new HashMap<>();
		Page<WSalary> page=new Page<>();

		//封装当前页数
		page.setCurrentPage(currentPage);

		//每页显示的数据
		int pageSize=5;
		page.setPageSize(pageSize);

		//封装总记录数
		int totalCount=wsalaryDao.selectWSalaryByDeptCounts(jdept);
		page.setTotalCount(totalCount);

		//封装总页数
		double t=totalCount;
		Double num=Math.ceil(t/pageSize);//向上取整
		page.setTotalPage(num.intValue());


		map.put("jdept",jdept);
		//封装起始和每页显示页数
		if((currentPage-1)*pageSize<0){
			map.put("start",0);
		}else {
			map.put("start",(currentPage-1)*pageSize);
		}
		map.put("size",page.getPageSize());

		//封装每页显示的数据
		List<WSalary> datas=wsalaryDao.selectWSalaryByDept(map);
		page.setDatas(datas);
		return page;
	}

	//添加工资信息
	@Override
	public void addWSalary(WSalary wsalary) {
		wsalaryDao.deleteWSalaryByWnoAndDate(wsalary);
		wsalaryDao.insertWSalary(wsalary);
	}

	@Override
	public void addWSalaryBatch(List<WSalary> wSalaryList) {
		wsalaryDao.insertWSalaryBatch(wSalaryList);
	}

	//删除工资信息
	@Override
	public void deleteWSalary(int[] wsidArray) {
		for (int i = 0; i < wsidArray.length; i++) {
			wsalaryDao.deleteWSalaryByWno(wsidArray[i]);
		}
	}

	//导出excel
	@Override
	public HSSFWorkbook toExcel(List<WSalary> list) {
		//创建excel文件
		HSSFWorkbook wbook=new HSSFWorkbook();
		//创建sheet页
		HSSFSheet sheet=wbook.createSheet("工资信息报表");
		//创建标题行
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("工号");
		titleRow.createCell(1).setCellValue("姓名");
		titleRow.createCell(2).setCellValue("职位编号");
		titleRow.createCell(3).setCellValue("职位名称");
		titleRow.createCell(4).setCellValue("所属部门");
		titleRow.createCell(5).setCellValue("基本工资");
		titleRow.createCell(6).setCellValue("奖金");
		titleRow.createCell(7).setCellValue("总工资");
		titleRow.createCell(8).setCellValue("工资月份");
		titleRow.createCell(9).setCellValue("是否已发放");
		titleRow.createCell(10).setCellValue("发放日期");

		//将数据放入excel
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

	//修改工资信息
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
			if(wsalary.getIsgrant().equals("否"))
				wsalaryDao.updateWSalaryAll();
		}*/
		wsalaryDao.updateWSalaryAll();
	}

	//清空
	@Override
	public void truncateWsalary() {
		wsalaryDao.truncate();
	}

}
