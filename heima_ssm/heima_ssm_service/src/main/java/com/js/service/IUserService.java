package com.js.service;

import com.js.domain.Role;
import com.js.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//继承此接口是为了使用security的规范
public interface IUserService extends UserDetailsService {
	//查询所有用户操作
	List<UserInfo> findAll();
    //添加用户
	void save(UserInfo userInfo);
    //根据ID查询用户
	UserInfo findById(String id);

	List<Role> findOtherRoles(String userId);

	void addRoleToUser(String userId, String[] roles);
}
