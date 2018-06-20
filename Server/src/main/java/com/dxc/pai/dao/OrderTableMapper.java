package com.dxc.pai.dao;

import com.dxc.pai.model.OrderTable;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderTableMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderTable record);

    OrderTable selectByPrimaryKey(String id);

    List<OrderTable> selectAll();

    int updateByPrimaryKey(OrderTable record);

    List<String> selectLatest(Integer number);
    
    List<OrderTable> selectFirst(Integer number);
    
    //List<OrderTable> getARange(@Param("op")String op, @Param("ed")String ed);
    
    List<OrderTable> getRange(@Param("start")Date start, @Param("end")Date end);

}