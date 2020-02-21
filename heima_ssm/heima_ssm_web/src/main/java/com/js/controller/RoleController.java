package com.js.controller;

import com.js.domain.Permission;
import com.js.domain.Role;
import com.js.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
     @Autowired
	 private IRoleService roleService;
     //角色添加
	@RequestMapping("/save.do")
	public String save(Role role){
		roleService.save(role);
		return"redirect:findAll.do";
	}
	//角色查询全部
	@RequestMapping("/findAll.do")
	public ModelAndView findAll(){
		ModelAndView mv=new ModelAndView();
		List<Role> all =roleService.findAll();
		mv.addObject("roleList",all);
		mv.setViewName("role-list");
		return mv;
	}
	//根据role的ID查询出role 并查询出其可以添加的权限
	@RequestMapping("/findRoleByIdAndAllPermission.do")
	public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
		ModelAndView mv=new ModelAndView();
		//根据ID查询role
		Role role=roleService.findById(roleId);
		//查询可以添加的权限
		List<Permission> permissions=roleService.findOtherPermissions(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
	}
	//添加权限
	@RequestMapping("/addPermissionToRole")
	public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds){
       roleService.addPermissionToRole(roleId,permissionIds);
       return "redirect:findAll.do";
	}
}
