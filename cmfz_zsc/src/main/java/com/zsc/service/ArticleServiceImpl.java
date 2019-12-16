package com.zsc.service;

import com.zsc.dao.ArticleDao;
import com.zsc.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    //获取集合
    @Override
    public List<Article> queryPage(Integer page, Integer rows) {
        Integer size = (page - 1) * rows;
        RowBounds rowBounds = new RowBounds(size,rows);
        List<Article> list = articleDao.selectByRowBounds(new Article(), rowBounds);
        return list;
    }

    //获取总条数
    @Override
    public int queryRecords() {
        return articleDao.selectCount(new Article());
    }

    //修改
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateArticle(Article article) {
        int i = articleDao.updateByPrimaryKeySelective(article);
        if(i!=1){
            throw  new RuntimeException("修改失败");
        }
        return article.getId();
    }

    //添加
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addArticle(Article article) {
        String id = UUID.randomUUID().toString();
        article.setId(id);
        Date date = new Date();
        article.setPublishDate(date);
        int i = articleDao.insertSelective(article);
        if(i != 1){
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    //删除
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delArticle(Article article) {
        int i = articleDao.deleteByPrimaryKey(article);
        if(i != 1){
            throw new RuntimeException("删除失败");
        }
    }


}
