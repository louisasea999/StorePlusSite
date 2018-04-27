package com.dxc.pai.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.pai.util.TimerManager;

@WebListener
public class RetrieveDataListener implements ServletContextListener{
	
	@Autowired
	private TimerManager tm;
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContex Initialized");
		//new TimerManager();
		tm.startTimer();
		System.out.println("timerManage started!");
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContex Destroyed");
	}

}
