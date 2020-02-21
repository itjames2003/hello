package com.js.service.impl;

import com.js.dao.IPermissionDao;
import com.js.domain.Permission;
import com.js.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService{
	@Autowired
	private IPermissionDao permissionDao;
	@Override
	public List<Permission> findAll() {
		return permissionDao.findAll();
	}

	@Override
	public void save(Permission permission) {
		permissionDao.save(permission);
	}
}
