package com.js.dao;

import com.js.domain.Member;
import com.js.domain.Orders;
import com.js.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
	@Select("select * from orders")
	@Results({
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "orderNum", column = "orderNum"),
			@Result(property = "orderTime", column = "orderTime"),
			@Result(property = "orderStatus", column = "orderStatus"),
			@Result(property = "peopleCount", column = "peopleCount"),
			@Result(property = "payType", column = "payType"),
			@Result(property = "orderDesc", column = "orderDesc"),
			@Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.js.dao.IProductDao.findById")),
	})
	List<Orders> findAll();

	//多表操作 查询订单的详情
	@Select("select * from orders where id=#{ordersId}")
	@Results({
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "orderNum", column = "orderNum"),
			@Result(property = "orderTime", column = "orderTime"),
			@Result(property = "orderStatus", column = "orderStatus"),
			@Result(property = "peopleCount", column = "peopleCount"),
			@Result(property = "payType", column = "payType"),
			@Result(property = "orderDesc", column = "orderDesc"),
			@Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.js.dao.IProductDao.findById")),
			@Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "com.js.dao.IMemberDao.findById")),
			@Result(property = "travellers", column = "id", javaType = java.util.List.class, many = @Many(select = "com.js.dao.ITravellerDao.findByOrdersId"))
	})
	public Orders findById(String ordersId);
}
