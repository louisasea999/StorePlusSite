package com.dxc.pai.util;

import java.io.File;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.pai.service.LoginSimulationService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Component
public class RetrieveDataTask extends TimerTask{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Autowired
	public LoginSimulationService ls;

	@Override
	public void run() {
		
		long curr = System.currentTimeMillis();
		ls.Login();
		ls.requestData(curr - 1000*60*60*24, curr);
		
		
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
