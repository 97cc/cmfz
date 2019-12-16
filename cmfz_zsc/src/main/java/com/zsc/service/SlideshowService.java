package com.zsc.service;

import com.zsc.entity.Slideshow;

import java.util.List;

public interface SlideshowService {

    //获取页面数据
    List<Slideshow> queryPage(Integer page, Integer rows);

    //总条数
    int queryRecords();

    //添加
    String addSlideshow(Slideshow slideshow);

    //删除
    void delSlideshow(Slideshow slideshow);

    //修改
    String editSlideshow(Slideshow slideshow);

    //获取所有数据
    List<Slideshow> slideshowAll();

    //添加名字
    //void update(Slideshow slideshow);
}
