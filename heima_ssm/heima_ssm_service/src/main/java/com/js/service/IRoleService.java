package com.js.service;

import com.js.domain.Permission;
import com.js.domain.Role;

import java.util.List;

public interface IRoleService {
  public List<Role> findAll();
  //添加角色
  void save(Role role);

  Role findById(String roleId);

  List<Permission> findOtherPermissions(String roleId);

  void addPermissionToRole(String roleId, String[] permissionIds);
}
