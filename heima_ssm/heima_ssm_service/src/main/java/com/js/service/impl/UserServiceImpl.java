package com.js.service.impl;

import com.js.dao.IUserDao;
import com.js.domain.Role;
import com.js.domain.UserInfo;
import com.js.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	//根据ID查询用户
	@Override
	public UserInfo findById(String id) {
		return userDao.findById(id);
	}

	//密码加密的Bean对象
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//查询所有用户操作
	@Override
	public List<UserInfo> findAll() {
		return userDao.findAll();
	}

	//用户添加
	@Override
	public void save(UserInfo userInfo) {
		//对密码进行加密
		userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
		userDao.save(userInfo);
	}
	//根据用户Id查询可添加的角色
	@Override
	public List<Role> findOtherRoles(String userId) {
		return userDao.findOtherRoles(userId);
	}
	//紧跟上面完成给用户添加角色
	@Override
	public void addRoleToUser(String userId, String[] roles) {
		for (String roleId:roles) {
			userDao.addRoleToUser(userId,roleId);
		}
	}

	//利用security完成用户的登录验证
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userDao.findByUsername(username);
		//将自己的用户对象封装为UserDetails 第三个参数为需要一个认证的权限对象
		User user = new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true, getAuthority(userInfo.getRoles()));
		return user;
	}
	//作用是返回一个List集合 集合中放权限角色的描述
	public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
       List<SimpleGrantedAuthority> list=new ArrayList<>();
		for (Role ro:roles) {
			list.add(new SimpleGrantedAuthority("ROLE_"+ro.getRoleName()));
		}
       return list;
	}


}
