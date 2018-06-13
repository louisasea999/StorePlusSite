package com.dxc.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.pai.model.RestResult;
import com.dxc.pai.service.FacesService;
import com.dxc.pai.util.ResultGenerator;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/Faces")
public class FacesController {

	@Value("${mysite}")
    private String mysite;
	
	@Autowired 
	private FacesService fs;
	
	@Autowired
	private ResultGenerator generator;
	
	
	 /**
     * 
     * 
     * @return 返回封装好的信息
     */
    @RequestMapping("/getGenderData")
    public @ResponseBody RestResult getGenderData() {
    	
    	try
    	{
    		JSONArray genderData = fs.getGenderData();
	        return generator.getSuccessResult("得到性别比例",genderData);
	        
    	}catch(Exception e)
    	{
    		 return generator.getFailResult("发生未知错误，无法得到性别比例");
    	}		
    }
    
    
    /**
     * 
     * 
     * @return 返回封装好的信息
     */
    @RequestMapping("/getAgeData")
    public @ResponseBody RestResult getAgeData() {
    	
    	try
    	{
    		JSONArray ageData = fs.getAgeData();
	        return generator.getSuccessResult("得到年龄分布数据",ageData);
	        
    	}catch(Exception e)
    	{
    		 return generator.getFailResult("发生未知错误，无法得到年龄分布数据");
    	}		
    }
}
