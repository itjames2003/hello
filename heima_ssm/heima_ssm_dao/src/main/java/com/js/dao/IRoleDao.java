package com.js.dao;

import com.js.domain.Permission;
import com.js.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
	//根据用户的ID查询出所有对应的角色
	@Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
	@Results({
			@Result(id = true,property = "id",column = "id"),
			@Result(property = "roleName",column = "roleName"),
			@Result(property = "roleDesc",column = "roleDesc"),
			@Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.js.dao.IPermissionDao.findPermissionByRoleId"))
	})
	public List<Role> findRoleByUserId(String userId);
    //查询所有角色
	@Select("select * from role")
	List<Role> findAll();
    //角色的添加
	@Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
	void save(Role role);
    @Select("select * from role where id=#{id}")
	Role findById(String roleId);
    //查询角色可添加的权限
	@Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{rolrId})")
	List<Permission> findOtherPermissions(String roleId);
    //添加权限
	@Insert("insert into role_permission(roleId,permissionId)values(#{roleId},#{permissionId})")
	void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String pp);
}
