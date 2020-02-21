package com.js.controller;

import com.js.domain.SysLog;
import com.js.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
	private ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
	public ModelAndView findAll(){
		ModelAndView mv=new ModelAndView();
		List<SysLog> logs= sysLogService.findAll();
		mv.addObject("sysLogs",logs);
		mv.setViewName("syslog-list");
		return mv;
	}
}
