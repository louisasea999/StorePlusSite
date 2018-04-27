package com.dxc.pai.util;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.pai.service.OrderService;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Component
public class RetrieveDataTask extends TimerTask{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Autowired
	private OrderService orderService;

	@Override
	public void run() {
		AnalogLogin al = new AnalogLogin(orderService);
		al.loginAndGetData();
		/*while(true)
		{
			if(AnalogLogin.login())
			{
				
				break;
			}
			else
			{
				AnalogLogin.identifyCount+=1;				
				AnalogLogin.cookie_str ="";
			}
		}
		System.out.println("identifyCount: "+AnalogLogin.identifyCount);
		long curr = System.currentTimeMillis();
		AnalogLogin.GetOrderDataByTime(curr - 1000*60*60*24, curr);*/
		
	}
	public String readimg(String imgPath) {
		try {
			System.out.println("parsing...");
			return new Tesseract().doOCR(new File(imgPath));
			//System.out.println(result);
			//return result;
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	

}
