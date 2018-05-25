package com.dxc.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dxc.pai.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	public OrderService orderService;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
}
