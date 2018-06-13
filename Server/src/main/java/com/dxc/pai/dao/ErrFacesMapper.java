package com.dxc.pai.dao;

import com.dxc.pai.model.ErrFaces;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ErrFacesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrFaces record);

    ErrFaces selectByPrimaryKey(Integer id);

    List<ErrFaces> selectAll();

    int updateByPrimaryKey(ErrFaces record);
}