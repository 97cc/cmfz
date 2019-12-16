package com.zsc.service;

import com.zsc.dao.AlbumDao;
import com.zsc.entity.Album;
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
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;

    //一页的数据
    @Override
    public List<Album> queryPage(Integer page, Integer rows) {
        Integer ss = (page-1)*rows;
        RowBounds rowBounds = new RowBounds(ss,rows);
        return  albumDao.selectByRowBounds(new Album(),rowBounds);
    }

    //总条数
    @Override
    public int querySize() {
        return albumDao.selectCount(new Album());
    }

    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addAlbum(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);//生成ID
        Date date = new Date();
        album.setCreateDate(date);
        int i = albumDao.insertSelective(album);
        if (i!=1){
            throw new RuntimeException("添加失败");
        }
        return s;
    }

    //删除
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delAlbum(Album album) {
        int i = albumDao.deleteByPrimaryKey(album);
        if (i!=1){
            throw new RuntimeException("删除失败");
        }
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String editAlbum(Album album) {
        String id = null;
        Date date = new Date();
        album.setLastUpdateDate(date);
        if(album.getCover().length()>0){//说明有图片i（需要上传）
            int i = albumDao.updateByPrimaryKeySelective(album);
            if (i!=1){
                throw new RuntimeException("修改失败");
            }
            return album.getId();
        }else{//说明无图片id（不需要上传）将ID设置为NULL
            album.setCover(null);
            int i = albumDao.updateByPrimaryKeySelective(album);
            if (i!=1){
                throw new RuntimeException("修改失败");
            }
            return id;
        }


    }

    //获取总计和
    @Override
    public List<Album> selectAll() {
        return albumDao.selectAll();
    }
}
