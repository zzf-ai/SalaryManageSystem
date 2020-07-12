package com.zzf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzf.po.RPrecord;
import com.zzf.service.RPrecordService;

/**
 * 奖惩记录模块
 *
 */
@Controller
public class RPrecordController {

	@Autowired
	private RPrecordService rprecordService;

	// 查询全部信息
	@RequestMapping("/RPrecordslist.action")
	public String RPrecordslist(Model model) {
		List<RPrecord> list = rprecordService.findAllRPrecord();
		model.addAttribute("rpRecordList", list);
		return "RPrecords/rpRecordslist";
	}

	// 关键词查找
	@RequestMapping("/RPrecordslistbyname.action")
	public String RPrecordslistByWname(Model model, String wname) {
		List<RPrecord> list = rprecordService.findRPrecordByWname(wname.trim());
		model.addAttribute("rpRecordList", list);
		return "RPrecords/rpRecordslist";
	}

	// 按工号查找
	@RequestMapping("/RPrecordslistbyno.action")
	public String RPrecordslistByWno(Model model, String wno) {
		List<RPrecord> list = rprecordService.findRPrecordByWno(wno.trim());
		model.addAttribute("rpRecordList", list);
		return "RPrecords/rpRecordslist";
	}

	// 添加
	@RequestMapping(value = "/RPrecordspreinsert.action", method = RequestMethod.GET)
	public String RPrecordsPreinsert() {
		return "RPrecords/rpRecordsadd";
	}

	@RequestMapping(value = "/RPrecordsinsert.action", method = RequestMethod.POST)
	public String RPrecordsInsert(RPrecord rprecord) {
		rprecordService.addRPrecord(rprecord);
		return "redirect:RPrecordslist.action";
	}

	// 删除
	@RequestMapping(value = "/RPrecordsdelete.action", method = RequestMethod.POST)
	public String RPrecordsDelete(String[] rpnoArray) {

		rprecordService.deleteRPrecord(rpnoArray);
		return "redirect:RPrecordslist.action";
	}

	// 修改
	@RequestMapping(value = "/RPrecordspreupdate.action", method = RequestMethod.GET)
	public String RPrecordsPreupdate(RPrecord rprecord, Model model) {
		model.addAttribute("rpRecords", rprecord);
		return "RPrecords/rpRecordsupdate";
	}

	@RequestMapping(value = "/RPrecordsupdate.action", method = RequestMethod.POST)
	public String RPrecordsUpdate(RPrecord RPrecord) {
		rprecordService.updateRPrecord(RPrecord);
		return "redirect:RPrecordslist.action";
	}
}
