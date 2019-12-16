package com.zsc.controller;

import com.zsc.dto.UserCount;
import com.zsc.entity.User;
import com.zsc.service.UserService;
import com.zsc.util.JqGrid;
import com.zsc.util.UpluadFile;
import io.goeasy.GoEasy;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("User")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("queryPage")
    @ResponseBody
    public  Map<String,Object> queryPage(Integer page, Integer rows){
        List<User> list =  userService.queryPage(page,rows);
        int records = userService.queryRecords();
        System.out.println(list.toString());
        Map<String, Object> map = JqGrid.getQueryPage(list, page, rows, records);
        return map;
    }


    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> editUser(String oper , User user){
        Map<String, Object> map = null;
        if(oper.equals("add")){ //添加
            try {
                String id = userService.addUser(user);
                map = JqGrid.success(id);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }if(oper.equals("del")){//删除
            try {
                userService.delUser(user);
                map = JqGrid.success(null);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }if(oper.equals("edit")){//修改
            try {
                String id = userService.editUser(user);
                map = JqGrid.success(id);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }
        return map;
    }
    @RequestMapping("upluad")
    public void upluad(String id, MultipartFile headImg, HttpServletRequest request){

        if(id != null && !id.equals("")) {
            UpluadFile upluadFile = new UpluadFile();
            Map<String, String> map = upluadFile.UpluadFile("userImg", headImg, request);
            String fileName = map.get("fileName");
            User user = new User();
            user.setId(id);
            user.setHeadImg(fileName);

            userService.editUser(user);
        }
    }

    @RequestMapping("resultUser")
    @ResponseBody
    public Map<String,Integer> resultUser(){
        Map<String, Integer> map = new HashMap<>();
        //int a  = 0;
        //本周
        List<User> list0 = userService.queryRegisterCount(0);
        //上一周
        List<User> list1 = userService.queryRegisterCount(1);
        //上上周
        List<User> list2 = userService.queryRegisterCount(2);
        map.put("week0",list0.size());
        map.put("week1",list1.size());
        map.put("week2",list2.size());
        return map;
    }
    //添加用户
    @RequestMapping("addUser")
    public void addUser(){
        User user = new User();
        user.setName("123");
        user.setPassword("213");
        user.setSex("男");
        user.setProvince("河南省");
        String id = userService.addUser(user);
        if(id != null){
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-5c87e20b04854bd590eb273711322ba1");
            goEasy.publish("my_cmfz","来啦，老弟！！！");
        }

    }

    //省份用户信息
    @RequestMapping("userCount")
    @ResponseBody
    public Map<String, List<UserCount>> userCount(){
        Map<String,  List<UserCount>> map = new HashMap<>();
        //获取男性
        List<UserCount> list1 = userService.queryUserProvinceCount("男");
        map.put("nen",list1);

        //获取女性
        List<UserCount> list2 = userService.queryUserProvinceCount("女");
        map.put("female",list2);

        System.out.println("sssss"+list1);

        for (UserCount userCount : list1) {
            System.out.println(userCount);
        }
        for (UserCount userCount : list2) {
            System.out.println(userCount);
        }
        return map;
    }







}
