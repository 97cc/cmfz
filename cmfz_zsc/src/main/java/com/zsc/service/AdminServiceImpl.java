package com.zsc.service;

import com.zsc.dao.AdminDao;
import com.zsc.entity.Admin;
import com.zsc.util.Md5UUIDSaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;


    @Override
    public String login(String name, String password) {
        Admin admin = new Admin();
        admin.setName(name);
        Admin admin1 = adminDao.selectOne(admin);
        System.out.println(admin1+"查出来的");
        if(admin1 != null){//判断账户是否存在
            //获取用户盐值salt
            String salt = admin1.getSalt();
            String password1 = admin1.getPassword();//数据库中存储的
            String s = salt + password + salt;//用户输入
            boolean b = Md5UUIDSaltUtil.checkPassword(s, password1);//验证密码是否正确
            if(b){
                System.out.println("登陆成功");
                return "0";  //登陆成功
            }else{
                return "2"; //密码错误
            }
        }else{
            return "1";  //账户错误
        }

    }

    //查询用户信息
    @Override
    public Admin queryAdmin(Admin admin) {
        Admin admin1 = adminDao.selectOne(admin);
        System.out.println(admin1);
        return admin1;
    }

    //更改管理员信息用户信息
    @Override
    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }
}
