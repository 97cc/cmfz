package com.zsc.service;

import com.zsc.dao.SlideshowDao;
import com.zsc.entity.Slideshow;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.RasterFormatException;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SlideshowServiceImpl implements SlideshowService {
    @Autowired
    SlideshowDao slideshowDao;

    //获取当前页的数据
    @Override
    public List<Slideshow> queryPage(Integer page, Integer rows) {
        Integer currentPage = (page - 1) * rows;//从第几条获取
        /*两个参数    下标    每页的条数*/
        RowBounds rowBounds = new RowBounds(currentPage, rows);

        List<Slideshow> slideshows = slideshowDao.selectByRowBounds(new Slideshow(), rowBounds);
        return slideshows;
    }

    //获取总条数
    @Override
    public int queryRecords() {
        return slideshowDao.selectAll().size();
    }

    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addSlideshow(Slideshow slideshow) {
        String uuid = UUID.randomUUID().toString();
        Date date = new Date();
        slideshow.setId(uuid);
        slideshow.setCreateDate(date);
        int i = slideshowDao.insertSelective(slideshow);
        if (i != 1) {
            throw new RuntimeException("添加失败");
        }

        return uuid;
    }

    //删除
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delSlideshow(Slideshow slideshow) {
        int i = slideshowDao.deleteByPrimaryKey(slideshow);
        if (i != 1) {
            throw new RuntimeException("添加失败");
        }

    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String editSlideshow(Slideshow slideshow) {
        String id = null;
        Date date = new Date();
        slideshow.setLastUpdateDate(date);//修改时的时间
        System.out.println(slideshow.toString());
        if (slideshow.getCover().length() > 0) {
            int i = slideshowDao.updateByPrimaryKeySelective(slideshow);
            if (i != 1) {
                throw new RuntimeException("添加失败");
            }
            return slideshow.getId();
        }else{
            slideshow.setCover(null);
            slideshowDao.updateByPrimaryKeySelective(slideshow);
            return id;
        }
    }


    @Override
    public List<Slideshow> slideshowAll() {
        return slideshowDao.selectAll();
    }


}
