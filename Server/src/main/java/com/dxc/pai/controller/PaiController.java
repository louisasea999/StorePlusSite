package com.dxc.pai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxc.pai.model.User;
import com.dxc.pai.service.OrderService;
import com.dxc.pai.service.UserService;
import com.dxc.pai.util.AnalogLogin;

@Controller
public class PaiController {

	@Value("${mysite}")
    private String mysite;
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public UserService us;

	
	
	
	//@ResponseBody默认值为
	@RequestMapping("/pai")
	public String index(HttpSession session, Model model){
		model.addAttribute("who", "Chad");
		//List<User> li = us.getAllUser();
		return "greet/hello";
	}
	
	@RequestMapping("/lu")
	public String listUser(HttpSession session, Model model) {
		model.addAttribute("ul", us.getAllUser());
		return "user/userlist";
	}
}
