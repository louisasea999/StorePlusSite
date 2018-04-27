package com.dxc.pai;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import javax.xml.ws.Endpoint;

import com.alibaba.druid.pool.DruidDataSource;
import com.dxc.pai.WS.DemoWebService;
import com.dxc.pai.WS.DemoWebServiceImpl;
import com.dxc.pai.service.OrderService;
import com.dxc.pai.util.AnalogLogin;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@SpringBootApplication
@ServletComponentScan
public class PaiApplication {

	
	@Value("${mysite}")
    private String mysite;
	
	
	@Autowired
    private Environment env;
	
	@Autowired
	private static OrderService os;
	
	//destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
    @Bean(destroyMethod =  "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setInitialSize(2);//初始化时建立物理连接的个数
        dataSource.setMaxActive(20);//最大连接池数量
        dataSource.setMinIdle(0);//最小连接池数量
        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
        dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }


    
    
    


	
	public static void main(String[] args) {
		SpringApplication.run(PaiApplication.class, args);
		
		
	
		
		//1521617854,1523861391
		//1523861901
		
		//1521617854,1523861391,1523957002
		//1523957031
		/*C8F36291061A1DC87A2D61148B1EA82D
		while(true) {
			try {
				//testGet();
				aPost();
				Thread.sleep(1000*60*10);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}
	
	public static void aPost() throws IOException {
		
		//JsonArray jsonArray = new JsonArray();
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("startTime", 1523721600000L);
		jsonObj.addProperty("endTime", 1523808000000L);
		jsonObj.addProperty("waiter","");
		jsonObj.addProperty("cashier","");
		jsonObj.addProperty("region","");
		jsonObj.addProperty("payTypeNum","");
		jsonObj.addProperty("freeTypeNum","");
		jsonObj.addProperty("tableNum","");
		jsonObj.addProperty("mtReserveChecked",false);
		jsonObj.addProperty("billSource","");
		jsonObj.addProperty("size",2);
		jsonObj.addProperty("start",0);
		System.out.println(jsonObj.toString());
		
		
		PrintWriter out = null;
		BufferedReader in = null;
		String urlPath = "http://irs.passiontec.cn/irs-api/zeus-web/bill-report/detail";
		
		//String cookie = "aliyungf_tc=AQAAAFdxl1VQhAUASE/At2Jeuw3fQbjH;IRSSID=6d71dae3-63cd-4ab7-8428-ef318ec81d43;Hm_lvt_58590919dd2c1aa361e8e0ea599148f9=1521617854,1523861391,1523957002;IRSEXPID=ifaceid_1#webid_1;PV=2.25.03;Hm_lpvt_58590919dd2c1aa361e8e0ea599148f9=1523957031";
		
		//String cookie = "aliyungf_tc=AQAAAIuXZTuOfwcASE/At8Q0x+pM8Osm;IRSSID=aec37964-ee74-4cbe-9370-8c8131051ed5;Hm_lvt_58590919dd2c1aa361e8e0ea599148f9=1521617854,1523861391,1523957002;IRSEXPID=ifaceid_1#webid_1;PV=2.25.03;Hm_lpvt_58590919dd2c1aa361e8e0ea599148f9=1523957031";
		String cookie = "IRSSID=3fdd921a-d135-4697-b312-1e8e4f3deb4a";
		URL url = new URL(urlPath);
		try {
			URLConnection conn = url.openConnection();
			//装配为http请求(还有https请求)
			HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
			
			// 设置通用的请求属性
			httpURLConnection.setRequestProperty("accept", "*/*");
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Cookie", cookie);
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(jsonObj.toString());
			// flush输出流的缓冲
			out.flush();
			
			//get status code
			System.out.println(httpURLConnection.getResponseCode());			
			String cookieVal =httpURLConnection.getHeaderField("Set-Cookie");
			System.out.println(cookieVal);
			
			
			
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			System.out.println(sb.toString());
			
		
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
	}

	public static void testPost() throws MalformedURLException {
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("username", "bypost");
		jsonObj.addProperty("password", "bypost");
		
		JsonObject user = new JsonObject();
		user.add("User",jsonObj);
		
		
		PrintWriter out = null;
		BufferedReader in = null;
		String urlPath = "http://13.76.191.189:8080/gd/pic/credential";
		//String cookie = "aliyungf_tc=AQAAAFdxl1VQhAUASE/At2Jeuw3fQbjH; IRSSID=6d71dae3-63cd-4ab7-8428-ef318ec81d43; Hm_lvt_58590919dd2c1aa361e8e0ea599148f9=1521617854,1523861391; IRSEXPID=ifaceid_1#webid_1; PV=2.25.03; Hm_lpvt_58590919dd2c1aa361e8e0ea599148f9=1523861901";
		URL url = new URL(urlPath);
		try {
			URLConnection conn = url.openConnection();
			//装配为http请求(还有https请求)
			HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
			
			// 设置通用的请求属性
			httpURLConnection.setRequestProperty("accept", "*/*");
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			
			//json type
			//httpURLConnection.setRequestProperty("Content-Type", "application/json");
			//form type
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			httpURLConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			httpURLConnection.setRequestMethod("POST");
			//httpURLConnection.setRequestProperty("Cookie", cookie);
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			//out.write(jsonObj.toString());
			out.write("pwd=edcxx");
			// flush输出流的缓冲
			out.flush();
			//get status code
			System.out.println(httpURLConnection.getResponseCode());
			String cookieVal = httpURLConnection.getHeaderField("Set-Cookie");
			System.out.println(cookieVal);
			
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			System.out.println(sb.toString());
			
		
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
	}

	public static void testGet() throws MalformedURLException {
		PrintWriter out = null;
		BufferedReader in = null;
		String urlPath = "http://irs.passiontec.cn/irs-web/login";
		//String cookie = "JSESSIONID=2B4B32F8CD9092FE3FC3094726F65AC7";
		URL url = new URL(urlPath);
		try {
			URLConnection conn = url.openConnection();
			//装配为http请求(还有https请求)
			HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
			
			// 设置通用的请求属性
			httpURLConnection.setRequestProperty("accept", "*/*");
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			
			//json type
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			//form type
			//httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			httpURLConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			httpURLConnection.setRequestMethod("GET");
			//httpURLConnection.setRequestProperty("Cookie", cookie);
			//get status code
			System.out.println(httpURLConnection.getResponseCode());
			String cookieVal =httpURLConnection.getHeaderField("Set-Cookie");
			//String JSESSIONID = (cookieVal.substring(0,cookieVal.indexOf(";")));
			System.out.println(cookieVal);
			
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			System.out.println(sb.toString());
			
		
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
	}

}
