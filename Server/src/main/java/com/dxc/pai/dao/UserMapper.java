package com.dxc.pai.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.dxc.pai.model.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}