package com.zsc.controller;

import com.zsc.dto.Photo;
import com.zsc.entity.Article;
import com.zsc.service.ArticleService;
import com.zsc.util.JqGrid;
import com.zsc.util.UpluadFile;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("Article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page,Integer rows){
        //获取集合
        List<Article> list = articleService.queryPage(page,rows);
        //获取总条数
        int records = articleService.queryRecords();
        Map<String, Object> map = JqGrid.getQueryPage(list, page, rows, records);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> queryPage(String oper, Article article) {
        System.out.println("|||||"+article);
        Map<String, Object> map = null;
        if("del".equals(oper)) {//删除
            try {
                articleService.delArticle(article);
                map = JqGrid.success(null);
            } catch (Exception e) {
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }else{//添加或者是修改
            if(article.getId().length()>0){//说明是修改
                try {
                    String id = articleService.updateArticle(article);
                    map = JqGrid.success(id);
                }catch (Exception e){
                    e.printStackTrace();
                    map = JqGrid.error(e);
                }
            }else{ //添加
                try {
                    String id = articleService.addArticle(article);
                    map = JqGrid.success(id);
                }catch (Exception e){
                    e.printStackTrace();
                    map = JqGrid.error(e);
                }
            }
        }
        return map;
    }


    @RequestMapping("browser")
    @ResponseBody
    public Map<String,Object> browser(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        map.put("moveup_dir_path","");
        map.put("current_dir_path","");

        String path = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/img/";

        map.put("current_url",path);

        String img = request.getSession().getServletContext().getRealPath("/img");
        File file = new File(img);
        File[] files = file.listFiles();

        map.put("total_count",files.length);

        List <Photo> list = new ArrayList<>();
        for (File f : files) {
            Photo photo = new Photo();
            boolean directory = f.isDirectory();
            if(directory){
                int length = f.listFiles().length;
                if(length == 0){
                    photo.setHas_file(false);
                }else {
                    photo.setHas_file(true);
                }
            }else{
                photo.setHas_file(false);
            }
            photo.setFilesize(f.length());
            photo.setDir_path("");
            String name = f.getName();
            photo.setFilename(name);
            String extension = FilenameUtils.getExtension(name);
            photo.setFiletype(extension);
            if(extension.equals("png")||extension.equals("jpg")||extension.equals("jpeg")){
                photo.setIs_photo(true);
            }else{
                photo.setIs_photo(false);
            }
            long l = f.lastModified();
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l));
            photo.setDatetime(format);
            list.add(photo);
        }
        map.put("file_list",list);
        return map;
    }


    @RequestMapping("upluad")
    @ResponseBody
    public Map<String,Object> upluad(MultipartFile imgFile, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File file = new File(realPath, "articleImg");
            if (!file.exists()) {
                file.mkdir();
            }
            imgFile.transferTo(new File(file,imgFile.getOriginalFilename()));
            String path = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/articleImg/"+imgFile.getOriginalFilename();
            map.put("error", 0);
            map.put("url", path);
            return map;
        }catch (Exception e){
            map.put("error",1);
            return map;
        }

    }



    @RequestMapping("update")
    public String updateArticle(@RequestBody String id){
        System.out.println("wwww"+id);
        return "qwe";
    }



}
