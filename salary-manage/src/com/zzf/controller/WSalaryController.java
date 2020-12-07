package com.zzf.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.po.Jobs;
import com.zzf.po.User;
import com.zzf.po.Workers;
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

import com.zzf.po.WSalary;
import com.zzf.service.WSalaryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 员工工资信息管理模块
 *
 */
@Controller
public class WSalaryController {

	@Autowired
	private WSalaryService wsalaryService;
	@Autowired
	private JobService jobService;

	// 全部查询
	@RequestMapping("/WSalaryslist.action")
	public String WSalaryslist(Model model) {
		List<WSalary> list = wsalaryService.findAllWSalary();
		model.addAttribute("WSalaryList", list);
		List<Jobs> jobs=jobService.findAllJobs();
		model.addAttribute("Jobs", jobs);
		return "WSalarys/WSalaryslist";
	}

	// 按姓名关键词查找
	@RequestMapping("/WSalaryslistbyname.action")
	public String WSalaryslistByWname(Model model, String wname) {
		List<WSalary> list = wsalaryService.findWSalaryByWname(wname.trim());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/WSalaryslist";
	}

	// 按工号查找
	@RequestMapping("/WSalaryslistbyno.action")
	public String WSalaryslistByWno(Model model, String wno) {
		List<WSalary> list = wsalaryService.findWSalaryByWno(wno.trim());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/WSalaryslist";
	}
	// 按部门查找
	@RequestMapping("/WSalaryslistbyJdept.action")
	public String WSalaryslistByJdept(Model model, String jdept) {
		List<WSalary> list = wsalaryService.findWSalaryByJDept(jdept.trim());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/WSalaryslist";
	}

	//查询个人工资
	@RequestMapping("/WSalaryslistbyno2.action")
	public String WSalaryslistByWno2(Model model, HttpSession session) {
		User user= (User) session.getAttribute("USER_SESSION");
		List<WSalary> list = wsalaryService.findWSalaryByWno(user.getWno());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/MySalaryslist";
	}

	//将工资信息存入数据库
	@RequestMapping(value = "/WSalarysinsert.action", method = RequestMethod.POST)
	@ResponseBody
	public String WSalarysInsert(@RequestBody String list) {
		wsalaryService.truncateWsalary();
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
	//添加
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

	// 删除
	@RequestMapping(value = "/WSalarysdelete.action", method = RequestMethod.POST)
	public String WSalarysDelete(String[] wnoArray) {
		wsalaryService.deleteWSalary(wnoArray);
		return "redirect:WSalaryslist.action";
	}

	// 修改
	@RequestMapping(value = "/WSalarysupdate.action", method = RequestMethod.POST)
	public String WSalarysUpdate(WSalary wsalary) {
		wsalaryService.updateWSalary(wsalary);
		return "redirect:WSalaryslist.action";
	}

	//导出excel表格
	@RequestMapping("/toexcel")
	public void excel(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		List<WSalary> list=wsalaryService.findAllWSalary();

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

		}

		//下载
		response.setContentType("application/octet-stream;charset=utf-8");
		//设置文件名

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		response.setHeader("Content-Disposition","attachment;filename="+new String((dateNowStr+"工资信息报表").getBytes(),"iso-8859-1")+".xls");
		OutputStream outputStream=response.getOutputStream();
		wbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	//异步验证
	@RequestMapping(value = "/ajaxCheck5.action",method = RequestMethod.POST)
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
	}
}
