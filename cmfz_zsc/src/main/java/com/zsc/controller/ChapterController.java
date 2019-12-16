package com.zsc.controller;

import com.zsc.entity.Chapter;
import com.zsc.service.ChapterService;
import com.zsc.util.JqGrid;
import com.zsc.util.UpluadFile;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

//章节表
@Controller
@RequestMapping("Chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page,Integer rows,String albumId){
        //获取集合
        List<Chapter> list = chapterService.queryPage(page,rows,albumId);
        //获取总条数
        int records = chapterService.queryRecords();
        Map<String, Object> map = JqGrid.getQueryPage(list, page, rows, records);
        return map;
    }
    @RequestMapping("editChapter")
    @ResponseBody
    public Map<String,Object> editChapter(String oper, Chapter chapter) {
        Map<String, Object> map = null;
        if("edit".equals(oper)){//修改updeatChapter(chapter)
            try {
                String id = chapterService.updeatChapter(chapter);
                map = JqGrid.success(id);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }else if("add".equals(oper)){//添加
            try {
                String id = chapterService.addChapter(chapter);
                map = JqGrid.success(id);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }else if("del".equals(oper)){//删除
            try {
            chapterService.delChapter(chapter);
                map = JqGrid.success(null);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }
        return map;

    }

    //上传文件
    @RequestMapping("upluad")
    public void upluad(String id, MultipartFile audioPath, HttpServletRequest request){
        System.out.println("+++++"+id);

        if(id != null && !id.equals("")){
            System.out.println("有文件");
            UpluadFile upluadFile = new UpluadFile();
            Map<String, String> map = upluadFile.UpluadFile("play", audioPath, request);
            String fileName = map.get("fileName");
            String fileSize = map.get("fileSize");
            String time = map.get("time");
            //将信息保存
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setAudioPath(fileName);
            chapter.setSize(fileSize);
            chapter.setDuration(time);
            //执行更改方法
            chapterService.updeatChapter(chapter);
        }
    }






}
