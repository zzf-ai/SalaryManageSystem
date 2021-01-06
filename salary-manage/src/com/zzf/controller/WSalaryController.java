package com.zzf.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.*;
import com.zzf.service.JobService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zzf.service.WSalaryService;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Ա��������Ϣ����ģ��
 *
 */
@Controller
public class WSalaryController {

	@Autowired
	private WSalaryService wsalaryService;
	@Autowired
	private JobService jobService;

	// ȫ����ѯ
	@RequestMapping("/WSalaryslist.action")
	public String WSalaryslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model) {
		Page<WSalary> page = wsalaryService.findAllWSalaryByPage(currentPage);
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = wsalaryService.findAllWSalaryByPage(currentPage+1);
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findAllWSalaryByPage(currentPage-1);
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",0);
		return "WSalarys/WSalaryslist";
	}

	// �������ؼ��ʲ���
	@RequestMapping("/WSalaryslistbySettleDate.action")
	public String WSalaryslistBySettleDate(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String settledate) {
		Page<WSalary> page = wsalaryService.findWSalaryBySettleDateByPage(currentPage,settledate.trim());
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryBySettleDateByPage(currentPage+1,settledate.trim());
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryBySettleDateByPage(currentPage-1,settledate.trim());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",1);
		return "WSalarys/WSalaryslist";
	}

	// �����Ų���
	@RequestMapping("/WSalaryslistbyWno.action")
	public String WSalaryslistByWno(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wno) {
		Page<WSalary> page = wsalaryService.findWSalaryByWno(currentPage,wno.trim());
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryByWno(currentPage+1,wno.trim());
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryByWno(currentPage-1,wno.trim());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",2);
		return "WSalarys/WSalaryslist";
	}
	// �����Ų���
	@RequestMapping("/WSalaryslistbyJdept.action")
	public String WSalaryslistByJdept(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jdept) {

		Page<WSalary> page = wsalaryService.findWSalaryByJDept(currentPage,jdept.trim());
		if(page.getDatas().size()==page.getPageSize()&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryByJDept(currentPage+1,jdept.trim());
		}

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryByJDept(currentPage-1,jdept.trim());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",3);
		return "WSalarys/WSalaryslist";
	}

	//��ѯ���˹���
	@RequestMapping("/WSalaryslistbyno2.action")
	public String WSalaryslistByWno2(Model model, HttpSession session) {
		User user= (User) session.getAttribute("USER_SESSION");
		List<WSalary> list = wsalaryService.findselectMySalary(user.getWno());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/MySalaryslist";
	}

	//��������Ϣ�������ݿ�
	@RequestMapping(value = "/WSalarysinsert.action", method = RequestMethod.POST)
	@ResponseBody
	public String WSalarysInsert(@RequestBody String list) {
		//wsalaryService.truncateWsalary();
		WSalary wsalary;
		System.out.println(JSONArray.fromObject(list).size());
		JSONArray ary=JSONArray.fromObject(list);
		System.out.println(ary.toString());
		JSONObject object;
		for (int i=1;i<ary.size();i++){
			object=ary.getJSONObject(i);
			wsalary= (WSalary) JSONObject.toBean(object,WSalary.class);
			wsalaryService.addWSalary(wsalary);
			System.out.println(wsalary);
		}
		return "true";
	}
	//���
	@RequestMapping(value = "/WSalarysinsert2.action", method = RequestMethod.POST)
	public String WSalarysInsert2(String wno,String wname,String jno) {
		String s1=jno.trim().split(",")[0];
		List<Jobs> jobs = jobService.findJobsByJno(s1);
		String s2=jno.trim().split(",")[1];
		String s3=jno.trim().split(",")[2];
		float s4=jobs.get(0).getJsalary();
		float s5=jobs.get(0).getJbonus();
		float total=s4+s5;
		WSalary wsalary=new WSalary();
		wsalary.setWno(wno);
		wsalary.setWname(wname);
		wsalary.setJno(s1);
		wsalary.setJname(s2);
		wsalary.setJdept(s3);
		wsalary.setJsalary(s4);
		wsalary.setJbonus(s5);
		wsalary.setTotal(total);
		wsalaryService.addWSalary(wsalary);
		return "redirect:WSalaryslist.action";
	}

	// ɾ��
	@RequestMapping(value = "/WSalarysdelete.action", method = RequestMethod.POST)
	public String WSalarysDelete(int[] wsidArray) {
		wsalaryService.deleteWSalary(wsidArray);
		return "redirect:WSalaryslist.action";
	}

	// ����ȫ��
	@ResponseBody
	@RequestMapping(value = "/WSalarysGrantAll.action", method = RequestMethod.POST)
	public String WSalarysGrantAll() {
		wsalaryService.grantWSalaryAll();
		return "true";
	}

	// ����ѡ�е�Ա��
	@ResponseBody
	@RequestMapping(value = "/WSalarysGrant.action", method = RequestMethod.POST)
	public String WSalarysGrant(int[] wsidArray) {
		wsalaryService.grantWSalary(wsidArray);
		return "true";
	}

	//����excel���
	@RequestMapping("/toexcel")
	public void excel(HttpServletResponse response,String settledate) throws IOException {
		response.setCharacterEncoding("UTF-8");
		List<WSalary> list;
		if(settledate!=null&&!settledate.equals("")){
			list=wsalaryService.findWSalaryBySettleDate(settledate);
		}
		else {
			list = wsalaryService.findAllWSalary();
		}
		//����excel�ļ�
		HSSFWorkbook wbook=wsalaryService.toExcel(list);
		/*//����sheetҳ
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
			dataRow.createCell(10).setCellValue(wsalary.getGrantdate());

		}*/

		//����
		response.setContentType("application/octet-stream;charset=utf-8");
		//�����ļ���

		/*Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);*/
		if(settledate!=null&&!settledate.equals(""))
			response.setHeader("Content-Disposition","attachment;filename="+new String((settledate+"������Ϣ����").getBytes(),"iso-8859-1")+".xls");
		else
			response.setHeader("Content-Disposition","attachment;filename="+new String(("�ִ��·ݹ�����Ϣ����").getBytes(),"iso-8859-1")+".xls");
		OutputStream outputStream=response.getOutputStream();
		wbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	//�첽��֤
	/*@RequestMapping(value = "/ajaxCheck5.action",method = RequestMethod.POST)
	@ResponseBody
	public String exam(@RequestBody JSONObject data) {
		String wno=data.getString("wno");
		System.out.println(wno);
		List<WSalary> wSalary= wsalaryService.findWSalaryByWno(wno);
		if((wSalary!=null&&wSalary.size()==1)){
			return "ok";
		}
		else
			return "false";
	}*/
}
