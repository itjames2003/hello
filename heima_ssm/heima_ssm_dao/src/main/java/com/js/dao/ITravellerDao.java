package com.js.dao;

import com.js.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//旅客
public interface ITravellerDao {
	@Select("select * from traveller where id in(select travellerId from order_traveller where orderId =#{ordersId})")
	public List<Traveller> findByOrdersId(String ordersId);
}
