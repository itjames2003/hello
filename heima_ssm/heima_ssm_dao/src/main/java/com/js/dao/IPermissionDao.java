package com.js.dao;

import com.js.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
	//根据role的ID查询出对应的权限
	@Select("select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
	public List<Permission> findPermissionByRoleId(String id);
    //查询全部权限
	@Select("select * from permission")
	List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
	void save(Permission permission);
}
