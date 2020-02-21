package com.js.service.impl;

import com.js.dao.IRoleDao;
import com.js.domain.Permission;
import com.js.domain.Role;
import com.js.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
	private IRoleDao roleDao;
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role findById(String roleId) {
		return roleDao.findById(roleId);
	}
	//查询可添加的权限

	@Override
	public List<Permission> findOtherPermissions(String roleId) {
		return roleDao.findOtherPermissions(roleId);
	}
	//添加权限

	@Override
	public void addPermissionToRole(String roleId, String[] permissionIds) {
		for (String pp:permissionIds) {
			roleDao.addPermissionToRole(roleId,pp);
		}
	}
}
