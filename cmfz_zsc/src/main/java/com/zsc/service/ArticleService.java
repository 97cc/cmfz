package com.zsc.service;

import com.zsc.entity.Article;

import java.util.List;

public interface ArticleService {
    //获取集合
    List<Article> queryPage(Integer page, Integer rows);

    //获取总条数
    int queryRecords();
    
    String updateArticle(Article article);

    String addArticle(Article article);

    void delArticle(Article article);
}
