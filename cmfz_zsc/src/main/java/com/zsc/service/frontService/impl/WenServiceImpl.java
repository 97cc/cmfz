package com.zsc.service.frontService.impl;

import com.zsc.dao.AlbumDao;
import com.zsc.dao.ChapterDao;
import com.zsc.entity.Album;
import com.zsc.entity.Chapter;
import com.zsc.service.frontService.WenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WenServiceImpl implements WenService {

    @Autowired
    AlbumDao albumDao;      //专辑
    @Autowired
    ChapterDao chapterDao;  //章节

    @Override
    public Map<String, Object> wen(String id, String uid) {
        Map<String, Object> map = new HashMap<>();
        try {
            //获取专辑介绍信息
            Album album = albumDao.selectByPrimaryKey(id);
            //获取章节信息
            Chapter chapter = new Chapter();
            chapter.setAlbumId(id);
            List<Chapter> select = chapterDao.select(chapter);
            map.put("code",200);
            map.put("introduction", album);
            map.put("list", select);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","参数错误");
        }

        return map;
    }
}
