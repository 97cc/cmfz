package com.zsc.service;

import com.zsc.entity.Album;

import java.util.List;

public interface AlbumService {
    //每页的数据
    List<Album> queryPage(Integer page, Integer rows);
    //总条数
    int querySize();
    //添加专辑
    String addAlbum(Album album);
    //删除专辑
    void delAlbum(Album album);
    //修改专辑
    String editAlbum(Album album);

    //获取专辑集合
    List<Album> selectAll();
}
