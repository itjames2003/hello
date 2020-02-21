package com.js.service;

import com.js.domain.Product;

import java.util.List;

public interface IProductService {
	//查询全部产品
	public List<Product> findAll();
     //添加产品
	public void save(Product product);
}
