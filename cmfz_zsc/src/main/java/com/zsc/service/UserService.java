package com.zsc.service;

import com.zsc.dto.UserCount;
import com.zsc.entity.User;

import java.util.List;

public interface UserService {
    //获取页面数据
    List<User> queryPage(Integer page, Integer rows);
    //获取总条数
    int queryRecords();

  
    String addUser(User user);

    void delUser(User user);

    String editUser(User user);

    //根据时间查询
    List<User> queryRegisterCount(int a);

    //根据性别查省份用户数量
    List<UserCount> queryUserProvinceCount(String sex);
}
