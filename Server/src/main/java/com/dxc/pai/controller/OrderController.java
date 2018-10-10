package com.dxc.pai.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.pai.model.RestResult;
import com.dxc.pai.service.OrderService;
import com.dxc.pai.util.ResultGenerator;


@RestController
@RequestMapping("/Order/table")
public class OrderController {

	@Value("${mysite}")
    private String mysite;
	
	@Autowired 
	private OrderService os;
	
	@Autowired
	private ResultGenerator generator;
	
	 /**
     * 匹配 /user/register 地址
     * 
     * 
     * @return 返回封装好的销售额信息
     */
    @RequestMapping("/getSaleDataPrices")
    public @ResponseBody RestResult getSaleDataPrice() {
    	
    	try
    	{
    		JSONArray sales = os.changeMapToJSONArray(os.getSalesData(false));
	        return generator.getSuccessResult("得到销售额",sales);
	        
    	}catch(Exception e)
    	{
    		 return generator.getFailResult("发生未知错误，无法得到销售额");
    	}		
    }
    
    /**
     * 得到销售量
     * @return
     */
    @RequestMapping("/getSaleDataNum")
    public @ResponseBody RestResult getSaleDataNum()
    {
    	try
    	{
    		JSONArray sales = os.changeMapToJSONArray(os.getSalesData(true));
	    	
	        return generator.getSuccessResult("得到销售量",sales);
	        
	    }catch(Exception e)
		{
			 return generator.getFailResult("发生未知错误，无法得到销售量");
		}
    }
	
    /**
     * 得到销售量百分比
     * @return
     */
    @RequestMapping("/getSaleDataNumPercent")
    public RestResult getSaleDataNumPercent()
    {
    	try{
    		
    		JSONArray sales = os.changeMapToJSONArray(os.getSalesDataPercent(true));
	        return generator.getSuccessResult("得到销售量百分比",sales);
	    }catch(Exception e)
		{
			 return generator.getFailResult("发生未知错误，无法得到售量百分比");
		}
    }
    
    /**
     * 得到销售额百分比
     * @return
     */
    @RequestMapping("/getSaleDataPricesPercent")
    public RestResult getSaleDataPricesPercent()
    {
    	try{
    		
    		JSONArray sales = os.changeMapToJSONArray(os.getSalesDataPercent(false));
	        return generator.getSuccessResult("得到销售额百分比",sales);
	    }catch(Exception e)
		{
			 return generator.getFailResult("发生未知错误，无法得到销售额百分比");
		}
    }
	
	  /**
     * 为参数验证添加异常处理器
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResult handleConstraintViolationException(ConstraintViolationException cve) {
        //这里简化处理了，cve.getConstraintViolations 会得到所有错误信息的迭代，可以酌情处理
        String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
        return generator.getFailResult(errorMessage);
    }

    @RequestMapping("/getOrderAndFace")
    public RestResult getOrderAndFace() {
    	RestResult rr = generator.getSuccessResult("OrderToFace",os.getOrderAndFace());
    	return rr;
    }
    @RequestMapping("/getFoodComb")
    public RestResult getFoodComb() {
    	RestResult rr = generator.getSuccessResult("FoodComb",os.getComb());
    	return rr;
    }
    @RequestMapping("/getFoodComb")
    public RestResult getTopTen() {
    	Date startDate = new Date(new Date().getTime() - 72*60*60*1000);
    	Date endDate = new Date();
    	RestResult rtt = generator.getSuccessResult("TopTen",os.getTopTen(startDate, endDate, 10));
    	return rtt;
    }

}
