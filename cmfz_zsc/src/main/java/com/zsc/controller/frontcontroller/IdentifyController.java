package com.zsc.controller.frontcontroller;

import com.zsc.util.Md5UUIDSaltUtil;
import com.zsc.util.SMSVerification;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("identify")
public class IdentifyController {


    @RequestMapping("obtain")
    @ResponseBody
    public void identify(String phone, HttpServletRequest request){
        HttpSession session = request.getSession();
        String salt = Md5UUIDSaltUtil.getSalt();
        session.setAttribute("salt",salt);
        session.setAttribute("phone",phone);
        SMSVerification.SMSVerification(phone,salt);
    }

    @RequestMapping("check")
    @ResponseBody
    public Map<String, String> check(String phone, String code, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();

        HttpSession session = request.getSession();
        String salt = (String) session.getAttribute("salt");
        String phone1 = (String) session.getAttribute("phone");
        if(phone1.equals(phone)){
            if(salt.equals(code)){
                map.put("result","success");
            }
        }
        return map;
    }
}
