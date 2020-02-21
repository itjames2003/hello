package com.js.service.impl;


import com.js.dao.IProductDao;
import com.js.domain.Product;
import com.js.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao iProductDao;
	@Override
	public List<Product> findAll() {
		return iProductDao.findall();
	}

	@Override
	public void save(Product product) {
		iProductDao.save(product);
	}
}
