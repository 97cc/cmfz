package com.zsc.service.frontService.impl;

import com.zsc.dao.*;
import com.zsc.entity.Album;
import com.zsc.entity.Article;
import com.zsc.entity.Slideshow;
import com.zsc.entity.User;
import com.zsc.service.frontService.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    SlideshowDao slideshowDao;  //轮播图
    @Autowired
    AlbumDao albumDao;      //专辑
    @Autowired
    ChapterDao chapterDao;  //段落
    @Autowired
    ArticleDao articleDao;  //文章
    @Autowired
    UserDao userDao;    //用户


    @Override
    public Map<String, Object> firstPage(String uid, String type, String sub_type) {

        Map<String, Object> map = new HashMap<>();//总集合
        Map<String, Object> body = new HashMap<>();//用于存放专辑与文章
        User user = userDao.selectByPrimaryKey(uid);//获取用户的上师ID
        String guruId = user.getGuruId();

        if (type.equals("all")) {//首页
            try {
                List<Slideshow> list = slideshowDao.selectPageHeader();//获取轮播图集合
                List<Album> album = albumDao.selectPagealbum();//获取专辑集合
                body.put("albums", album);
                if (guruId != null) {//判断用户是否已经有上师
                    List<Article> article = articleDao.selectPageArticle(guruId);//获取上市的文章
                    body.put("articles", article);
                } else {
                    body.put("articles", null);
                }
                map.put("code", 200);
                map.put("header", list);

                map.put("body", body);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", 500);
                map.put("msg", "首页获取数据错误!");

            }

        } else if (type.equals("wen")) {  //闻页面获取所有专辑
            try {
                List<Album> albums = albumDao.selectAll();
                body.put("albums", albums);
                map.put("code", 200);
                map.put("body", body);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", 500);
                map.put("msg", "数据错误！");

            }
        } else if (type.equals("si")) {//思 页面
            if (sub_type.equals("ssyj")) {//上师言教
                try {
                    if (guruId != null) {//说明有上师
                        List<Article> article = articleDao.selectByGutuId(guruId);//获取上市的文章
                        body.put("articles", article);
                    } else {
                        body.put("articles", null);
                    }
                    map.put("code", 200);
                    map.put("body", body);
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("code", 500);
                    map.put("msg", "参数错误！");
                }
            } else {
                try {
                    List<Article> article = articleDao.selectNoGutuId(guruId);//其他上师的专辑
                    body.put("articles", article);
                    map.put("code", 200);
                    map.put("body", body);
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("code", 500);
                    map.put("msg", "参数错误！");

                }
            }
        }


        return map;


    }


    @Override
    public Map<String, Object> queryDetail(String id, String uidc) {
        Map<String, Object> map = new HashMap<>();

        try {

            Article article = articleDao.selectByPrimaryKey(id);
            String content = article.getContent();
            map.put("code", 200);
            map.put("content", content);
            map.put("id", id);
            map.put("ext", null);
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","参数错误");

        }
        return map;
    }
}
