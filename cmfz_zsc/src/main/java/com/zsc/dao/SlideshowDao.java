package com.zsc.dao;

import com.zsc.entity.Slideshow;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface SlideshowDao extends Mapper<Slideshow> {


    @Select("SELECT * FROM cmfz_slideshow where STATUS = 1 LIMIT 5 ")
    List<Slideshow> selectPageHeader();


}
