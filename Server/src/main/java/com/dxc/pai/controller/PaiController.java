package com.dxc.pai.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dxc.pai.service.LoginSimulationService;
import com.dxc.pai.service.UserService;

@Controller
public class PaiController {

	@Value("${mysite}")
    private String mysite;
	
	@Autowired
	public UserService us;

	@Autowired
	public LoginSimulationService ls;
	
	@RequestMapping("/")
	public String index(){
		System.out.println("1111");
		
//		long curr = System.currentTimeMillis();
//		ls.Login();
//		ls.requestData(curr - 1000*60*60*24, curr);
		
		
		return "hello";
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
