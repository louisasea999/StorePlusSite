package com.dxc.pai.dao;

import com.dxc.pai.model.FindAllfaces;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FindAllfacesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FindAllfaces record);

    FindAllfaces selectByPrimaryKey(Integer id);

    List<FindAllfaces> selectAll();

    int updateByPrimaryKey(FindAllfaces record);
    
    List<String> selectAllFace();
}