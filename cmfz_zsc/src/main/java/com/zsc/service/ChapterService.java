package com.zsc.service;

import com.zsc.entity.Chapter;

import java.util.List;

public interface ChapterService {
    //获取页面数据
    List<Chapter> queryPage(Integer page, Integer rows,String albumId);
    //获取总条数
    int queryRecords();
    //修改
    String updeatChapter(Chapter chapter);
    //添加
    String  addChapter(Chapter chapter);
    //删除
    void delChapter(Chapter chapter);
    //获取总集和
    List<Chapter> queryAll(Chapter chapter);
}
