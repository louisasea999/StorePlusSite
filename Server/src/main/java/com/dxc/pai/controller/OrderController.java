package com.dxc.pai.controller;

import javax.validation.ConstraintViolationException;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public RestResult getSaleDataPrice() {
    	try
    	{
	    	JSONArray sales = JSONArray.fromObject(os.getSalesData(false));
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
    public RestResult getSaleDataNum()
    {
    	try
    	{
	    	JSONArray sales = JSONArray.fromObject(os.getSalesData(true));
	        return generator.getSuccessResult("得到销售量",sales);
	        
	    }catch(Exception e)
		{
			 return generator.getFailResult("发生未知错误，无法得到销售额");
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
    		
	    	JSONArray sales = JSONArray.fromObject(os.getSalesDataPercent(true));
	        return generator.getSuccessResult("得到销售量百分比",sales);
	    }catch(Exception e)
		{
			 return generator.getFailResult("发生未知错误，无法得到销售额");
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
    		
	    	JSONArray sales = JSONArray.fromObject(os.getSalesDataPercent(false));
	        return generator.getSuccessResult("得到销售额百分比",sales);
	    }catch(Exception e)
		{
			 return generator.getFailResult("发生未知错误，无法得到销售额");
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

	
}
