package com.zsc.controller;

import com.zsc.entity.Slideshow;
import com.zsc.service.SlideshowService;
import com.zsc.util.JqGrid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("slideshow")
public class SlideshowController {

    @Autowired
    SlideshowService slideshowService;

    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String ,Object> queryAll(Integer page,Integer rows){
        //获取总集合page当前页rows行数
        List<Slideshow> list = slideshowService.queryPage(page,rows);
        //总条数
        int records = slideshowService.queryRecords();
        Map<String, Object> map = JqGrid.getQueryPage(list, page, rows, records);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public  Map<String, Object> editSlideshow( String oper ,Slideshow slideshow){
        Map<String, Object> map = null;
        if(oper.equals("add")){ //添加
            try {
                String id = slideshowService.addSlideshow(slideshow);
                map = JqGrid.success(id);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }if(oper.equals("del")){//删除
            try {
                slideshowService.delSlideshow(slideshow);
                map = JqGrid.success(null);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }if(oper.equals("edit")){//修改
            try {
                String id = slideshowService.editSlideshow(slideshow);
                map = JqGrid.success(id);
            }catch (Exception e){
                e.printStackTrace();
                map = JqGrid.error(e);
            }
        }
        return map;
    }

    @RequestMapping("upluad")
    public void upluad(String id, MultipartFile cover, HttpServletRequest request) throws IOException {


        if(id!=null){
            //将图片保存在项目文件夹中
            //获取项目名
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File file1 = new File(realPath + "img");
            if (!file1.exists()) {
                file1.mkdir();
            }
            String originalFilename = cover.getOriginalFilename();//获取图片原始名字
            //通过文件原始名 获取 扩展名
            String extension = FilenameUtils.getExtension(originalFilename);
            String name = "." + extension;
            String names = UUID.randomUUID().toString() + name;//生成图片的新名字
            //将新名字保存
            Slideshow slideshow = new Slideshow();
            slideshow.setId(id);

            slideshow.setCover(names);
            slideshowService.editSlideshow(slideshow);

            cover.transferTo(new File(file1.getPath(), names));//保存图片

        }
    }




    //导出Excel表
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        //创建对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建表
        HSSFSheet sheet = workbook.createSheet("轮播图");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String str[] = {"编号","标题","图片","状态","上传时间","修改时间"};

        //创建单元格并将表头添加进去(将第一行的标题赋值进去)
        HSSFCell cell = null;
        for (int i = 0; i < str.length; i++) {
            cell =  row.createCell(i);
            cell.setCellValue(str[i]);
        }
        //处理日期格式
        // 修饰表格的样式的对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //修饰日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日 HH时mm分ss秒"));//添加日期格式

        //获取到需要添加的数据
        List<Slideshow> list = slideshowService.slideshowAll();
        //向每一行添加数据
        for (int i = 0; i < list.size(); i++) {
            //从第几行插入数据
            row = sheet.createRow(i + 1);
            //向单元格中添加数据
            row.createCell(0).setCellValue(list.get(i).getId());
            row.createCell(1).setCellValue(list.get(i).getTitle());
            row.createCell(2).setCellValue(list.get(i).getCover());
            row.createCell(3).setCellValue(list.get(i).getStatus());

            //规定两个时间类型的显示样式
            cell = row.createCell(4);
            cell.setCellValue(list.get(i).getCreateDate());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5);
            cell.setCellValue(list.get(i).getCreateDate());
            cell.setCellStyle(cellStyle);
        }

        String filename = URLEncoder.encode("banner.xls");
        response.setHeader("content-disposition","attachment;filename="+filename);
        response.setContentType("application/vnd.ms-excel");
        //输出此表格
        try {
            //Date date = new Date();
            //String format = new SimpleDateFormat("yyyy-MM-dd-HH时mm分ss秒SSSS").format(date);
            //workbook.write(new File("C:\\Users\\lenovo\\Desktop\\新建文件夹 (2)/"+format+"轮播图数据表.xls"));
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //导入Excel表
    @RequestMapping("uploadExcel")
    public void uploadExcel(MultipartFile file) {
        try {
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("轮播图");
            int firstRowNum = sheet.getLastRowNum();
            for (int i = 0; i < firstRowNum; i++) {
                Row row = sheet.getRow(i + 1);
                String id = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                String cover = row.getCell(2).getStringCellValue();
                int status = (int) row.getCell(3).getNumericCellValue();
                Date createDate = row.getCell(4).getDateCellValue();
                Date lastUpdateDate = row.getCell(5).getDateCellValue();
                Slideshow slideshow = new Slideshow(id, title, cover, status, createDate, lastUpdateDate);
                System.out.println("||||||" + slideshow);
                //导入数据库
                String s = slideshowService.addSlideshow(slideshow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
