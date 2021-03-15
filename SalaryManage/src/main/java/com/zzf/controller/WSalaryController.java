package com.zzf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zzf.pojo.Jobs;
import com.zzf.pojo.Page;
import com.zzf.pojo.User;
import com.zzf.pojo.WSalary;
import com.zzf.service.JobService;
import com.zzf.service.WSalaryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * ������Ϣ����ģ��
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

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findAllWSalaryByPage(currentPage-1);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = wsalaryService.findAllWSalaryByPage(page.getTotalPage());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",0);
		return "WSalarys/WSalaryslist";
	}

	// �������·ݲ���
	@RequestMapping("/WSalaryslistbySettleDate.action")
	public String WSalaryslistBySettleDate(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String settledate) {
		Page<WSalary> page = wsalaryService.findWSalaryBySettleDateByPage(currentPage,settledate.trim());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryBySettleDateByPage(currentPage-1,settledate.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryBySettleDateByPage(page.getTotalPage(),settledate.trim());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",1);
		model.addAttribute("settledate",settledate);
		return "WSalarys/WSalaryslist";
	}

	// �����Ų���
	@RequestMapping("/WSalaryslistbyWno.action")
	public String WSalaryslistByWno(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String wno) {
		Page<WSalary> page = wsalaryService.findWSalaryByWno(currentPage,wno.trim());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryByWno(currentPage-1,wno.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryBySettleDateByPage(page.getTotalPage(),wno.trim());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",2);
		model.addAttribute("wno",wno);
		return "WSalarys/WSalaryslist";
	}

	// �����Źؼ��ֲ���
	@RequestMapping("/WSalaryslistbyJdept.action")
	public String WSalaryslistByJdept(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, String jdept) {

		Page<WSalary> page = wsalaryService.findWSalaryByJDept(currentPage,jdept.trim());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryByJDept(currentPage-1,jdept.trim());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryBySettleDateByPage(page.getTotalPage(),jdept.trim());
		}
		model.addAttribute("WSalaryList", page);
		model.addAttribute("flag4",3);
		model.addAttribute("jdept",jdept);
		return "WSalarys/WSalaryslist";
	}

	//��ѯ���˹���
	@RequestMapping("/MySalaryslist.action")
	public String MySalaryslist(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, HttpSession session) {
		User user= (User) session.getAttribute("USER_SESSION");
		Page<WSalary> page = wsalaryService.findWSalaryByWno(currentPage,user.getWno());

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findWSalaryByWno(currentPage-1,user.getWno());
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = wsalaryService.findWSalaryByWno(page.getTotalPage(),user.getWno());
		}
		model.addAttribute("flag5",0);
		model.addAttribute("WSalaryList", page);
		return "WSalarys/MySalaryslist";
	}

	//�����·ݲ�ѯ���˹���
	@RequestMapping("/MySalaryslistBySettleDate.action")
	public String MySalaryslistBySettleDate(@RequestParam(value = "currentPage",defaultValue = "1",required = false) int currentPage,Model model, HttpSession session,String settledate) {
		User user= (User) session.getAttribute("USER_SESSION");
		Page<WSalary> page = wsalaryService.findMySalaryBySettleDateByPage(currentPage,user.getWno(),settledate);

		if(page.getDatas().size()==0&&currentPage!=1){
			page = wsalaryService.findMySalaryBySettleDateByPage(currentPage-1,user.getWno(),settledate);
		}
		if(page.getDatas().size()==0&&currentPage>page.getTotalPage()){
			page = wsalaryService.findMySalaryBySettleDateByPage(page.getTotalPage(),user.getWno(),settledate);
		}
		model.addAttribute("flag5",1);
		model.addAttribute("WSalaryList", page);
		model.addAttribute("settledate", settledate);
		return "WSalarys/MySalaryslist";
	}

	//��������Ϣ�������ݿ�
	@RequestMapping(value = "/WSalarysinsert.action", method = RequestMethod.POST)
	@ResponseBody
	public String WSalarysInsert(HttpServletRequest request) {
		String param = request.getParameter("param");
		System.out.println(param);
		JSONObject jsonObject = JSON.parseObject(param);
		System.out.println(jsonObject);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		System.out.println(jsonArray.toJSONString());
		jsonArray.remove(0);
		List<WSalary> wSalaryList = JSON.parseObject(jsonArray.toJSONString(),new TypeReference<List<WSalary>>(){});
		System.out.println(wSalaryList.size());
		wsalaryService.addWSalaryBatch(wSalaryList);
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

		//����
		response.setContentType("application/octet-stream;charset=utf-8");
		//�����ļ���

		if(settledate!=null&&!settledate.equals("")) {
			response.setHeader("Content-Disposition","attachment;filename="+new String((settledate+"������Ϣ����").getBytes(),"iso-8859-1")+".xls");
		} else {
			response.setHeader("Content-Disposition","attachment;filename="+new String(("�ִ��·ݹ�����Ϣ����").getBytes(),"iso-8859-1")+".xls");
		}
		OutputStream outputStream=response.getOutputStream();
		wbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

}
