package com.zsc.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.zsc.entity.Album;
import com.zsc.entity.Chapter;
import com.zsc.entity.Slideshow;
import com.zsc.service.AlbumService;
import com.zsc.service.ChapterService;
import com.zsc.util.JqGrid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("Aldum")
public class AlbumController {

    @Autowired
    AlbumService albumService;
    @Autowired
    ChapterService chapterService;

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page, Integer rows){
        //获取当前页的集合
        List<Album> list = albumService.queryPage(page,rows);
        //获取纵条数
        int records = albumService.querySize();
        Map<String, Object> map = JqGrid.getQueryPage(list, page, rows, records);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> editAlbum(String oper, Album album) {
        System.out.println(album);
        Map<String,Object> map = new HashMap<>();
        if(oper.equals("add")){ //添加
            String id = albumService.addAlbum(album);
            System.out.println("信息ID"+id);
            map.put("status","ok");
            map.put("id",id);
        }if(oper.equals("del")){//删除
            albumService.delAlbum(album);
            map.put("status","ok");
        }if(oper.equals("edit")){//修改
            String id = albumService.editAlbum(album);
            map.put("status","ok");
            map.put("id",id);
        }
        return map;
    }

    @RequestMapping("upluad")
    public void upluad(String id, MultipartFile cover, HttpServletRequest request) throws IOException {

        if(id != null && !id.equals("")){

            //动态获取项目名及文件地址
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File file = new File(realPath + "album");
            if (!file.exists()) {
                file.mkdir();
            }
            //获取文件的原始名字
            String originalFilename = cover.getOriginalFilename();
            //获取原始名字的后缀
            String extension = FilenameUtils.getExtension(originalFilename);
            //生成文件的新名字
            String name = UUID.randomUUID().toString() + "." + extension;
            //保存图片
            cover.transferTo(new File(file.getPath(),name));

            //将新名字保存数据库
            Album album = new Album();
            album.setId(id);
            album.setCover(name);
            albumService.editAlbum(album);
        }
    }




    //导出表
    @RequestMapping("albumExcel")
    public void albumExcel(HttpServletRequest request, HttpServletResponse response){

        List<Album> albums = albumService.selectAll();

        Chapter chapter = new Chapter();

        for (Album album : albums) {
            String id = album.getId();
            chapter.setAlbumId(id);
            List<Chapter> chapters = chapterService.queryAll(chapter);
            String companyLogo = album.getCover();

            String realPath = request.getSession().getServletContext().getRealPath("/album/"+companyLogo);
            album.setCover(realPath);
            album.setChapters(chapters);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("专辑信息表","专辑"), Album.class, albums);

        String filename = URLEncoder.encode("album.xls");
        response.setHeader("content-disposition","attachment;filename="+filename);
        response.setContentType("application/vnd.ms-excel");

        try {
            workbook.write(response.getOutputStream());
            //workbook.write(new FileOutputStream("C:/Users/lenovo/Desktop/新建文件夹 (2)/"+"album.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //导入表
    @RequestMapping("uploadExcel")
    public void albumExcel(MultipartFile file){
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setKeyIndex(0);
            ImportParams params2 = new ImportParams();
            params2.setKeyIndex(12);
            params2.setTitleRows(1);
            params2.setHeadRows(2);
            long start = new Date().getTime();

            List<Album> list = ExcelImportUtil.importExcel(file.getInputStream(),
                    Album.class, params);

            List<Chapter> list1 = ExcelImportUtil.importExcel(file.getInputStream(),
                    Chapter.class, params2);

            System.out.println(new Date().getTime() - start);
            System.out.println("AAAAAA"+list.size());
            System.out.println("BBBBBB"+list1.size());
            System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
            System.out.println(list.toString());
            System.out.println(list1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}
