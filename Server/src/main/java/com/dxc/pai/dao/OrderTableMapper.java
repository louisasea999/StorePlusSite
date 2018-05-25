package com.dxc.pai.dao;

import com.dxc.pai.model.OrderTable;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface OrderTableMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderTable record);

    OrderTable selectByPrimaryKey(String id);

    List<OrderTable> selectAll();

    int updateByPrimaryKey(OrderTable record);
    
    List<String> selectLatest(Integer number);
    
    List<String> selectAllFoodDetail();
}