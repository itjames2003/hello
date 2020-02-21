package com.js.controller;

import com.github.pagehelper.PageInfo;
import com.js.domain.Orders;
import com.js.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private IOrdersService ordersService;
    //正常查询全部
//	@RequestMapping("/findAll.do")
//	public ModelAndView findAll() {
//		ModelAndView mv = new ModelAndView();
//		List<Orders> all = ordersService.findAll();
//		mv.addObject("ordersList", all);
//		mv.setViewName("orders-list");
//		return mv;
//	}
    //实现分页查询
	@RequestMapping("/findAll.do")
	public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue = "1")Integer page,@RequestParam(name ="size",required = true,defaultValue = "4")Integer size) {
		ModelAndView mv = new ModelAndView();
		List<Orders> all = ordersService.findAll(page,size);
		//PageInfo就是一个分页Bean
		PageInfo pageInfo=new PageInfo(all);
		mv.addObject("pageInf", pageInfo);
		mv.setViewName("orders-page-list");
		return mv;
	}
	//订单详情查询 根据ID
	@RequestMapping("/findById.do")
	public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId){
          ModelAndView mv=new ModelAndView();
          Orders orders=ordersService.findById(ordersId);
          mv.addObject("orders",orders);
          mv.setViewName("orders-show");
          return mv;
	}
}
