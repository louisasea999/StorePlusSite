package com.dxc.pai.util;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;


public class AnalogLogin {

	public static String cookie_str="";
	public static int identifyCount = 1;	

	public AnalogLogin()
	{
		tryLogin();
	}
	
	
	private void tryLogin()
	{
		while(true)
		{
			if(login())
			{				
				break;
			}
			else
			{
				identifyCount+=1;				
				cookie_str ="";
			}
		}
		System.out.println("identifyCount: "+identifyCount);		
	}
	/**
	 * get identify code and cookies
	 * */
	public static String getSecretCode()
	{
		String urlPath = "http://irs.passiontec.cn/irs-web/captcha";
		String code=null;
		try {
			URL url = new URL(urlPath);  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();             

            connection.setConnectTimeout(5000); 
            connection.setReadTimeout(10000); 
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 5.0;Windows NT;DigExt)"); 
            connection.connect(); 
           
            String key = null;
	        for(int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++)
			{  
            	String temp=null;
	            if(key.equalsIgnoreCase("Set-cookie"))
	            {  
	            	temp = connection.getHeaderField(i);  
	            	temp = temp.substring(0, temp.indexOf(";")); 
	            	temp+="; ";
	            	cookie_str+=temp;	            	
	            }  
		     }  
	        //System.out.println("cookie: "+cookie_str);		
            File imageFile = new File("src/main/resources/static/pic/codeImage.jpg");
            if (imageFile.exists()) {
            	imageFile.delete();
            }
            InputStream is = connection.getInputStream();  
            byte[] bs = new byte[1024];  
            int len;  
            OutputStream os = new FileOutputStream(imageFile);  
            // 开始读取  
            while ((len = is.read(bs)) != -1) {  
                os.write(bs, 0, len);  
            }
            is.close();
            os.close();                 		
			
			code = MyDealImage.identifyCode();
			System.out.println("code: "+code);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}
	 
	/**
	 * login in 
	 */
	public static boolean login() 
	{
		//get secret code
		String secretcode = getSecretCode();
		
		String loginInfor = "userName=xingwangchaocaiguan&employeeName=&password=888888&validateCode=";
		loginInfor+=secretcode;
		
		PrintWriter out = null;
		BufferedReader in = null;
		String urlPath = "http://irs.passiontec.cn/irs-web/login";
		
		URL url;
		try {
			url = new URL(urlPath);	
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(50000); 
            connection.setReadTimeout(10000); 
            connection.setRequestMethod("POST"); 			
			connection.setRequestProperty("Cookie",cookie_str);
			connection.setDoOutput(true);
			connection.setDoInput(true);		
			connection.connect(); 
			
			out = new PrintWriter(connection.getOutputStream());
			out.print(loginInfor);
			out.flush();		
			
			String key = null;
	        for(int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++)
			{  	        	
	            	String temp=null;
		            if(key.equalsIgnoreCase("Set-cookie"))
		            {  
		            	temp = connection.getHeaderField(i);  
		            	temp = temp.substring(0, temp.indexOf(";")); 
		            	temp+=";";
		            	cookie_str+=temp;		            	
		            }  
		     }  		
			System.out.println("cookie: "+cookie_str);		
			
			//get response data
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			JSONObject responseJson = JSONObject.fromObject(sb.toString());			
			if(responseJson.get("errorCode").toString().equals("10010"))
			{		
				return false;
			}						
			return true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(out!=null){
	                out.close();
	            }
				if(in!=null){
	                in.close();
	            }
			
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	
	public static String GetOrderDetailByID(String id)
	{
		String result= "";
		PrintWriter out = null;
		BufferedReader in = null;
		String urlPath = "http://irs.passiontec.cn/irs-api/zeus-web/bill-report/info/"+id;
		HttpURLConnection httpURLConnection  = getURLConnection(urlPath,"GET");
		try {
			
			httpURLConnection.connect();
			//get status code
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			result =  sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null){
	                out.close();
	            }
				if(in!=null){
	                in.close();
	            }
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	private static HttpURLConnection getURLConnection(String urlPath, String requestMethod)
	{
		URL url;
		URLConnection conn = null;
		HttpURLConnection httpURLConnection = null;
		try {
			url = new URL(urlPath);
			conn = url.openConnection();
			httpURLConnection = (HttpURLConnection)conn;
			httpURLConnection.setRequestProperty("accept", "*/*");
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			httpURLConnection.setRequestMethod(requestMethod);
			httpURLConnection.setRequestProperty("Cookie", cookie_str);
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		return httpURLConnection;		
	}
	
	
	public static String GetOrderDataByTime(Long startTime, Long endTime)
	{
		String result = "";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("startTime", startTime);
		jsonObj.put("endTime", endTime);
		jsonObj.put("waiter","");
		jsonObj.put("cashier","");
		jsonObj.put("region","");
		jsonObj.put("payTypeNum","");
		jsonObj.put("freeTypeNum","");
		jsonObj.put("tableNum","");
		jsonObj.put("mtReserveChecked",false);
		jsonObj.put("billSource","");
		jsonObj.put("size",2);
		jsonObj.put("start",0);
		//System.out.println(jsonObj.toString());		
		PrintWriter out = null;
		BufferedReader in = null;
		String urlPath = "http://irs.passiontec.cn/irs-api/zeus-web/bill-report/detail";
		
		HttpURLConnection httpURLConnection  = getURLConnection(urlPath,"POST");	
		httpURLConnection.setDoInput(true);
		httpURLConnection.setDoOutput(true);		
		
		try {
			httpURLConnection.connect(); 
			out = new PrintWriter(httpURLConnection.getOutputStream());
			out.print(jsonObj.toString());
			out.flush();
			
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}		
			System.out.println(sb.toString());	
			result = sb.toString();
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out!=null){
	                out.close();
	            }
				if(in!=null){
	                in.close();
	            }
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}		
		
		return result;
	}

}
