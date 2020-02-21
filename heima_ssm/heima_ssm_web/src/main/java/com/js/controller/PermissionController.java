package com.js.controller;

import com.js.domain.Permission;
import com.js.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;
	//资源权限
	@RequestMapping("/findAll.do")
	public ModelAndView findAll(){
		ModelAndView mv=new ModelAndView();
		List<Permission> all = permissionService.findAll();
		mv.addObject("permissionList",all);
		mv.setViewName("permission-list");
		return mv;
	}
	//权限的添加
	@RequestMapping("/save.do")
	public String save(Permission permission){
		permissionService.save(permission);
		return "redirect:findAll.do";
	}
}
