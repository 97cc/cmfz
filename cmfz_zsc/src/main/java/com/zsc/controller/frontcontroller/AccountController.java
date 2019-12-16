package com.zsc.controller.frontcontroller;

import com.zsc.dao.UserDao;
import com.zsc.entity.User;
import com.zsc.util.Md5UUIDSaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    UserDao userDao;

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(String phone, String password, String code, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = null;
        try {
            user.setPhone(phone);

            user = userDao.selectByPrimaryKey(user);

            map.put("password", user.getPassword());
            map.put("farmington", user.getDharma());
            map.put("uid", user.getId());
            map.put("nickname", user.getName());
            map.put("gender", user.getSex());
            map.put("photo", user.getHeadImg());
            map.put("location", user.getProvince() + " " + user.getCity());
            map.put("province", user.getProvince());
            map.put("city", user.getCity());
            map.put("description", user.getSign());
            map.put("phone", user.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", -200);
        }

        if (password != null && !password.equals("")) {//说明密码登陆
            String password1 = user.getPassword();
            boolean equals = password.equals(password1);
            if (equals) {
                return map;
            } else {
                map.put("errmsg", "密码错误");
                return map;
            }

        } else {//说明验证码登陆
            HttpSession session = request.getSession();
            String code1 = (String) session.getAttribute("code");
            boolean equals = code1.equals(code);
            if (equals) {
                return map;
            } else {
                map.put("errmsg", "密码错误");
                return map;
            }
        }


    }


    @RequestMapping("regist")
    @ResponseBody
    public Map<String, Object> regist(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setPhone(phone);
        User user1 = userDao.selectOne(user);
        if(user1!=null){
            map.put("error","-200");
            map.put("errmsg","该手机号已被注册");
        }else{
            String md5Code = Md5UUIDSaltUtil.createMd5Code(password);
            String salt = Md5UUIDSaltUtil.getSalt();
            String s = md5Code + salt + md5Code;
            String md5Code1 = Md5UUIDSaltUtil.createMd5Code(s);
            User user2 = new User();
            String s1 = UUID.randomUUID().toString();
            user2.setId(s1);
            user2.setPhone(phone);
            user2.setPassword(md5Code1);
            int i = userDao.insertSelective(user2);
            map.put("password",md5Code1);
            map.put("uid",s1);
            map.put("phone",phone);
        }
        return map;
    }

    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(User user) {
        Map<String, Object> map = new HashMap<>();
        try{
            if(user.getPassword()!=null){//判断是否更改密码
                String md5Code = Md5UUIDSaltUtil.createMd5Code(user.getPassword());
                String salt = Md5UUIDSaltUtil.getSalt();
                String s = md5Code + salt + md5Code;
                String md5Code1 = Md5UUIDSaltUtil.createMd5Code(s);
                user.setPassword(md5Code1);
            }
            if(user.getHeadImg().length()>0){  //判断头像是否修改
                int i = userDao.updateByPrimaryKeySelective(user);
            } else{
                user.setHeadImg(null);
                int i = userDao.updateByPrimaryKeySelective(user);
            }
            String id = user.getId();
            User user1 = userDao.selectByPrimaryKey(id);
            map.put("password", user1.getPassword());
            map.put("farmington", user1.getDharma());
            map.put("uid", user1.getId());
            map.put("nickname", user1.getName());
            map.put("gender", user1.getSex());
            map.put("photo", user1.getHeadImg());
            map.put("location", user1.getProvince() + " " + user1.getCity());
            map.put("province", user1.getProvince());
            map.put("city", user1.getCity());
            map.put("description", user1.getSign());
            map.put("phone", user1.getPhone());
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","-200");
            map.put("errmsg","该手机号已经存在");
        }
        return null;
    }







}