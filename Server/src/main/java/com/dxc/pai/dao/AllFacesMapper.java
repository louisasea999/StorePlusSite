package com.dxc.pai.dao;

import com.dxc.pai.model.AllFaces;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AllFacesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AllFaces record);

    AllFaces selectByPrimaryKey(Integer id);

    List<AllFaces> selectAll();

    int updateByPrimaryKey(AllFaces record);
    
    List<AllFaces> getPersons();
}