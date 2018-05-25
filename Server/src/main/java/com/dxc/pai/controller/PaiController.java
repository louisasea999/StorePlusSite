package com.dxc.pai.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dxc.pai.service.LoginSimulationService;
import com.dxc.pai.service.OrderService;
import com.dxc.pai.service.UserService;

@Controller
public class PaiController {

	@Value("${mysite}")
    private String mysite;
	
	@Autowired
	public UserService us;

	@Autowired
	public OrderService orderService;

	@Autowired
	public LoginSimulationService ls;
	
	@RequestMapping("/")
	public String indexHM(HttpSession session, Model model){
		System.out.println("1111");
		
<<<<<<< HEAD
//		long curr = System.currentTimeMillis();
//		ls.Login();
//		ls.requestData(curr - 1000*60*60*24, curr);
		
		
		return "hello";
=======
		//long curr = System.currentTimeMillis();
		//ls.Login();
		//ls.requestData(curr - 1000*60*60*24, curr);
		orderService.sele(10).stream().forEach(ele -> model.addAttribute("order", ele));

		//model.addAttribute("who", "Chad");
		return "index";
>>>>>>> c40b9de48573c013f20d20c4e70edf3d9aa51a6e
	}
	
	
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
