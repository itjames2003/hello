package com.js.service.impl;

import com.github.pagehelper.PageHelper;
import com.js.dao.OrdersDao;
import com.js.domain.Orders;
import com.js.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
	@Autowired
	private OrdersDao ordersDao;
	@Override
	public List<Orders> findAll(int page,int size) {
		PageHelper.startPage(page,size);
		return ordersDao.findAll();
	}

	@Override
	public Orders findById(String ordersId) {
		return ordersDao.findById(ordersId);
	}
}
