package com.zsc.service;

import com.zsc.entity.Admin;

public interface AdminService {

    /*登陆*/
    public String login(String name,String password);


    /*获取用户信息*/
    public Admin queryAdmin(Admin admin);



    /*更改用户信息*/
    public void update(Admin admin);

}
