package com.js.controller;

import com.js.domain.Role;
import com.js.domain.UserInfo;
import com.js.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	//查询指定ID的用户详情
	@RequestMapping("/findById.do")
	public ModelAndView findById(String id) {
		ModelAndView mv = new ModelAndView();
		UserInfo userInfo = userService.findById(id);
		mv.addObject("user", userInfo);
		mv.setViewName("user-show");
		return mv;
	}

	//添加用户
	@RequestMapping("/save.do")
	public String save(UserInfo userInfo) {
		userService.save(userInfo);
		return "redirect:findAll.do";
	}

	//查询所有用户
	@RequestMapping("/findAll.do")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		List<UserInfo> userInfos = userService.findAll();
		mv.addObject("userList", userInfos);
		mv.setViewName("user-list");
		return mv;
	}

	//完成用户关联角色 就是往关联表插入数据（查询用户以及用户可以添加的角色）
	@RequestMapping("/findUserByIdAndAllRole.do")
	public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) {
		ModelAndView mv = new ModelAndView();
		//根据ID查询用户
		UserInfo userInfo = userService.findById(userId);
		//根据ID查询用户可添加的角色
		List<Role> otherRoles = userService.findOtherRoles(userId);
		mv.addObject("user", userInfo);
		mv.addObject("roleList", otherRoles);
		mv.setViewName("user-role-add");
		return mv;
	}

	//紧跟上面 完成给用户添加角色（也就是往中间表中插入数据,ids代表可以添加的角色id）
	@RequestMapping("/addRoleToUser.do")
	public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roles) {
       userService.addRoleToUser(userId,roles);
       return"redirect:findAll.do";
	}
}
