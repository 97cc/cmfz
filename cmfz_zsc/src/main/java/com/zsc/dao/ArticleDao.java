package com.zsc.dao;

import com.zsc.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface ArticleDao extends Mapper<Article> {


    @Select("SELECT * from cmfz_article where guru_id = #{uid} order by publish_date desc limit 2")
    List<Article> selectPageArticle(@Param("uid") String uid);

    @Select("SELECT * from cmfz_article where guru_id != #{guruId} order by publish_date desc")
    List<Article> selectNoGutuId(@Param("guruId")String guruId);

    @Select("SELECT * from cmfz_article where guru_id = #{guruId} order by publish_date desc")
    List<Article> selectByGutuId(@Param("guruId")String guruId);
}
