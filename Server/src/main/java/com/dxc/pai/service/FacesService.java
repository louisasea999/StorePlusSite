package com.dxc.pai.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.dao.AllFacesMapper;
import com.dxc.pai.model.AllFaces;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("facesService")
public class FacesService {

	@Autowired
	private AllFacesMapper allface; 

	
	/**
	 * 得到性别比例
	 * @return
	 */
	public JSONArray getGenderData()
	{
		JSONArray sexData = new JSONArray();
		List<AllFaces> persons = allface.getPersons();
		int count = 0;
		int male = 0, female = 0;
		for(int i=0;i<persons.size();i++)
		{
			AllFaces person = persons.get(i);
			count++;
			if(person.getGender().equals("Male"))
			{
				male++;
			}
			else
				female++;			
		}
		JSONObject maleObj = new JSONObject();
		maleObj.put("gender", "male");
		maleObj.put("number", male);
		JSONObject famaleObj = new JSONObject();
		famaleObj.put("gender", "female");
		famaleObj.put("number", female);
		sexData.add(maleObj);
		sexData.add(famaleObj);
		//System.out.println(sexData);
		return sexData;
		
	}
	
	/**
	 * 得到年龄比例
	 * @return
	 */
	public JSONArray getAgeData()
	{
		JSONArray ageData = new JSONArray();
		List<AllFaces> persons = allface.getPersons();
		Map<Integer, Integer> ages = new HashMap<Integer, Integer>();
		for(int j=0;j<persons.size();j++)
		{
			int count = 1;
			AllFaces person = persons.get(j);
			int age = person.getAge();			
			if(ages.containsKey(age))
			{
				//System.out.println("have this age");
				count  = (int) ages.get(age) +1 ;					
			}			
						
			ages.put(age, count);
			//System.out.println(ages);
		}	
		ageData = getJSONArrayByMap(ages);
		//System.out.println(ageData);
		return ageData;
		
	}
	
	private JSONArray getJSONArrayByMap(Map<Integer, Integer> ages)
	{
		
		JSONArray result = new JSONArray();
		for(Map.Entry<Integer, Integer> entry : ages.entrySet()) { 
			JSONObject temp = new JSONObject();
			temp.put("age",entry.getKey());
			//System.out.println(entry.getKey());
			temp.put("number", entry.getValue());
			result.add(temp);
		}
		return result;
		
	}
}
