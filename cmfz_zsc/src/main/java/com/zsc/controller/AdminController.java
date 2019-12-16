package com.zsc.controller;

import com.zsc.entity.Admin;
import com.zsc.service.AdminService;
import com.zsc.util.CreateValidateCode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("Admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    //验证码
    @RequestMapping("create")
    public void coreate(HttpSession session,HttpServletResponse response) throws IOException {
        CreateValidateCode createValidateCode = new CreateValidateCode();
        BufferedImage image = createValidateCode.getImage();//图片
        String code = createValidateCode.getString();//验证码
        session.setAttribute("code",code);//保存验证码
        //将图片响应到页面
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
    }


    //用户登录
    @RequestMapping("login")
    @ResponseBody
    public String login(Admin admin ,String enCode, HttpServletRequest request){
            HttpSession session = request.getSession();
            String code = (String) session.getAttribute("code");
            if (code.equals(enCode)){//验证码输入正确
                String login = adminService.login(admin.getName(), admin.getPassword());
                if(login.equals("0")){//登陆成功
                    session.setAttribute("admin",admin);//将登陆成功的管理员保存
                    return "0";
                }else{
                    if(login.equals("1")){//账户错误
                        return "1";
                    }else{//密码错误
                        return "2";
                    }
                }
            }else{
                return "3";
            }
    }

    //安全退出
    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:back/login";
    }

    //查询管理员信息
    @RequestMapping("queryAdmin")
    public String queryAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin+"保存");
        Admin admin1 = adminService.queryAdmin(admin);
        request.setAttribute("admin1",admin1);
        return "admin1";
    }



    //修改个人信息
    @RequestMapping("updateAdmin")
    public String updateAdmin(Admin admin){
        adminService.update(admin);
        return "";
    }











}
