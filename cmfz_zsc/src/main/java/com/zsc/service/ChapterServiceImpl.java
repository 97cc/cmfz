package com.zsc.service;

import com.zsc.dao.ChapterDao;
import com.zsc.entity.Chapter;
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
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterDao chapterDao;

    //获取页面数据
    @Override
    public List<Chapter> queryPage(Integer page, Integer rows, String albumId) {
        Integer size = (page - 1) * rows;
        RowBounds rowBounds = new RowBounds(size, rows);
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        List<Chapter> list = chapterDao.selectByRowBounds(chapter, rowBounds);
        return list;
    }

    //获取总条数
    @Override
    public int queryRecords() {
        int i = chapterDao.selectCount(new Chapter());
        return i;
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String updeatChapter(Chapter chapter) {
        String id = null;
        Date date = new Date();
        chapter.setLastUpdateDate(date);
        if (chapter.getAudioPath().length() > 0) {//有图片
            int i = chapterDao.updateByPrimaryKeySelective(chapter);
            if(i != 1){
                throw new RuntimeException("有图片修改失败");
            }
            id = chapter.getId();
            return id;
        } else {
            chapter.setAudioPath(null);
            int i = chapterDao.updateByPrimaryKeySelective(chapter);
            if(i != 1){
                throw new RuntimeException("无图片修改失败");
            }
            return id;
        }

    }

    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addChapter(Chapter chapter) {
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        int i = chapterDao.insertSelective(chapter);
        if(i != 1){
            throw new RuntimeException("添加失败");
        }
        return id;

    }

    //删除
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delChapter(Chapter chapter) {
        int i = chapterDao.deleteByPrimaryKey(chapter);
        if(i != 1){
            throw new RuntimeException("删除失败");
        }
    }

    //获取总集和
    @Override
    public List<Chapter> queryAll(Chapter chapter) {
        return chapterDao.select(chapter);
    }


}
