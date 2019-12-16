package com.zsc.controller.frontcontroller;

import com.zsc.service.frontService.WenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("detail")
public class WenController {

    @Autowired
    WenService wenService;

    @RequestMapping("wen")
    @ResponseBody
    public Map<String,Object> wen(String id, String uid){
        System.out.println(id+uid);
        Map<String,Object> map = wenService.wen(id,uid);
        return map;
    }

}
