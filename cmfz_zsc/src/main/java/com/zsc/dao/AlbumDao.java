package com.zsc.dao;

import com.zsc.entity.Album;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface AlbumDao extends Mapper<Album> {

    @Select("select * from cmfz_album  where STATUS=1 order by publish_date desc limit 6")
    List<Album> selectPagealbum();
}
