package com.js.dao;

import com.js.domain.Role;
import com.js.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface IUserDao {
	@Select("select * from users where username=#{username}")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(property = "username",column = "username"),
			@Result(property = "email",column = "email"),
			@Result(property = "password",column = "password"),
			@Result(property = "phoneNum",column = "phoneNum"),
			@Result(property = "status",column = "status"),
			@Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.js.dao.IRoleDao.findRoleByUserId"))

	})
	public UserInfo findByUsername(String username);
	//查询所有用户操作
	@Select("select * from users")
	public List<UserInfo> findAll();
    //用户添加
	@Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
	void save(UserInfo userInfo);
	//根据ID查询用户
	@Select("select * from users where id=#{id}")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(property = "username",column = "username"),
			@Result(property = "email",column = "email"),
			@Result(property = "password",column = "password"),
			@Result(property = "phoneNum",column = "phoneNum"),
			@Result(property = "status",column = "status"),
			@Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.js.dao.IRoleDao.findRoleByUserId"))

	})
	UserInfo findById(String id);
    //根据用户ID查询可添加的角色
	@Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
	List<Role> findOtherRoles(String userId);
   //给用户添加角色
	@Insert("insert into users_role(userId,roleId)values(#{userId},#{roleId})")
	void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
