package com.dxc.pai.dao;

import com.dxc.pai.model.AllFacesets;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AllFacesetsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AllFacesets record);

    AllFacesets selectByPrimaryKey(Integer id);

    List<AllFacesets> selectAll();

    int updateByPrimaryKey(AllFacesets record);

}