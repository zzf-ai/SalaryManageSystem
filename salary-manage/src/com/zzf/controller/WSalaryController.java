package com.zzf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzf.po.WSalary;
import com.zzf.service.WSalaryService;

/**
 * Ա��������Ϣ����ģ��
 *
 */
@Controller
public class WSalaryController {

	@Autowired
	private WSalaryService wsalaryService;

	// ȫ����ѯ
	@RequestMapping("/WSalaryslist.action")
	public String WSalaryslist(Model model) {
		List<WSalary> list = wsalaryService.findAllWSalary();
		model.addAttribute("WSalaryList", list);
		return "WSalarys/WSalaryslist";
	}

	// �������ؼ��ʲ���
	@RequestMapping("/WSalaryslistbyname.action")
	public String WSalaryslistByWname(Model model, String wname) {
		List<WSalary> list = wsalaryService.findWSalaryByWname(wname.trim());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/WSalaryslist";
	}

	// �����Ų���
	@RequestMapping("/WSalaryslistbyno.action")
	public String WSalaryslistByWno(Model model, String wno) {
		List<WSalary> list = wsalaryService.findWSalaryByWno(wno.trim());
		model.addAttribute("WSalaryList", list);
		return "WSalarys/WSalaryslist";
	}

	// ���
	@RequestMapping(value = "/WSalaryspreinsert.action", method = RequestMethod.GET)
	public String WSalarysPreinsert() {
		return "WSalarys/WSalarysadd";
	}

	@RequestMapping(value = "/WSalarysinsert.action", method = RequestMethod.POST)
	public String WSalarysInsert(WSalary wsalary) {
		wsalaryService.addWSalary(wsalary);
		return "redirect:WSalaryslist.action";
	}

	// ɾ��
	@RequestMapping(value = "/WSalarysdelete.action", method = RequestMethod.POST)
	public String WSalarysDelete(String[] wnoArray) {

		wsalaryService.deleteWSalary(wnoArray);
		return "redirect:WSalaryslist.action";
	}

	// �޸�
	@RequestMapping(value = "/WSalaryspreupdate.action", method = RequestMethod.GET)
	public String WSalarysPreupdate(WSalary wsalary, Model model) {
		model.addAttribute("WSalarys", wsalary);
		return "WSalarys/WSalarysupdate";
	}

	@RequestMapping(value = "/WSalarysupdate.action", method = RequestMethod.POST)
	public String WSalarysUpdate(WSalary wsalary) {
		wsalaryService.updateWSalary(wsalary);
		return "redirect:WSalaryslist.action";
	}

}
