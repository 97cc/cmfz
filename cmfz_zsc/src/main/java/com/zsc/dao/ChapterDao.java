package com.zsc.dao;

import com.zsc.entity.Chapter;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ChapterDao extends Mapper<Chapter> {

}
