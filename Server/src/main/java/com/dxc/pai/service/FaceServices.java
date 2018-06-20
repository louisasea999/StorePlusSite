package com.dxc.pai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pai.dao.FindAllfacesMapper;
import com.dxc.pai.model.FindAllfaces;

@Service("faceService")
public class FaceServices {

	@Autowired
	private FindAllfacesMapper findAllfacesMapper;
	
	public List<FindAllfaces> selectAllFaceData(){
		return findAllfacesMapper.selectAll();
	}
	
	public int updateFace(FindAllfaces record) {
		return findAllfacesMapper.updateByPrimaryKey(record);
	}
	
	public List<String> selectAllFaces(){
		return findAllfacesMapper.selectAllFace();
	}
}
