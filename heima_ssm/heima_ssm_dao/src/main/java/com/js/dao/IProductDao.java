package com.js.dao;

import com.js.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {
	//根据ID查询 以方便多对一时在@One写select语句
	@Select("select * from product where id=#{id}")
	public Product findById(String id);
	//查询所有产品
	@Select("select * from product")
	public List<Product> findall();

	@Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
	public void save(Product product);
}
