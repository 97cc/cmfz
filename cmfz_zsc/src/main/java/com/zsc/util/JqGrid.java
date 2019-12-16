package com.zsc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JqGrid {

    private static final String CODE = "code";
    private static final String SUCCESS_CODE_VALUE = "200";
    private static final String ERROR_CODE_VALUE = "500";

    private static final String SUCCESS_ID = "id";
    private static final String ERROR_MSG= "msg";


    private static final String QUERY_PAGE_ROWS = "rows";
    private static final String QUERY_PAGE_PAGE = "page";
    private static final String QUERY_PAGE_TOTAL = "total";
    private static final String QUERY_PAGE_RECORDS = "records";


    public static Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>();
        return map;
    }

    /**
     * 当前页的数据信息
     * @param list    获取的集合
     * @param page    当前页
     * @param rows    每页的行数
     * @param records 总条数
     * @return
     */
    public static Map<String,Object> getQueryPage(List list,Integer page,Integer rows,Integer records){
        Map<String, Object> map = getMap();
        map.put(QUERY_PAGE_ROWS,list);
        map.put(QUERY_PAGE_PAGE,page);
        map.put(QUERY_PAGE_TOTAL,records%rows == 0 ? records / rows : records / rows + 1);
        map.put(QUERY_PAGE_RECORDS,records);
        return map;
    }

    /**
     * 成功
     * @param id
     * @return
     */
    public static Map<String,Object> success(String id){
        Map<String, Object> map = getMap();
        map.put(CODE,SUCCESS_CODE_VALUE);
        map.put(SUCCESS_ID,id);
        return map;
    }

    /**
     * 失败
     * @param e  错误
     * @return
     */
    public static Map<String,Object> error(Exception e){
        Map<String, Object> map = getMap();
        map.put(CODE,ERROR_CODE_VALUE);
        map.put(ERROR_MSG,e.getMessage());
        return map;
    }










}
