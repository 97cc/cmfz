package com.zsc.service.frontService;

import java.util.Map;

public interface HomePageService {


    Map<String, Object> firstPage(String uid, String type, String sub_type);


    Map<String, Object> queryDetail(String id, String uidc);
}
