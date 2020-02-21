package com.js.service;

import com.js.domain.Orders;

import java.util.List;

public interface IOrdersService {
	List<Orders> findAll(int page,int size);

	public Orders findById(String ordersId);
}
