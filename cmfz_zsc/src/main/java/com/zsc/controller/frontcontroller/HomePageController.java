package com.zsc.controller.frontcontroller;

import com.zsc.service.frontService.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    @RequestMapping("first_page")
    @ResponseBody
    public Map<String, Object> page(String uid, String type, String sub_type) {
        Map<String, Object> map = homePageService.firstPage(uid, type, sub_type);
        return map;
    }

    @RequestMapping("detail")
    @ResponseBody
    public Map<String, Object> detail(String id, String uidc) {
        Map<String, Object> map = homePageService.queryDetail(id, uidc);
        return map;
    }
}
