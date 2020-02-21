package com.js.service;

import com.js.domain.Permission;

import java.util.List;

public interface IPermissionService {
	//查询全部权限
	public List<Permission> findAll();
    //权限添加
	void save(Permission permission);
}
