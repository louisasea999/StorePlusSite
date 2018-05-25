package com.dxc.pai.dao;

import com.dxc.pai.model.Foodcomb;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodcombMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Foodcomb record);

    Foodcomb selectByPrimaryKey(Integer id);
    
    Foodcomb selectByComb(String comb);

    List<Foodcomb> selectAll();

    int updateByPrimaryKey(Foodcomb record);
}