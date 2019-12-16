package com.zsc;


import com.zsc.dao.UserDao;
import com.zsc.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CmfzZscApplication.class)
@RunWith(SpringRunner.class)
public class CmfzZscApplicationTests {
    @Autowired
    UserDao userDao;

    @Test
    public void asd() throws IOException {
        /*创建对象*/
        HSSFWorkbook workbook = new HSSFWorkbook();
        /*创建表     参数为表明*/
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String a[] = {"id", "用户名", "密码", "性别", "日期"};
        //创建单元格 对象
        HSSFCell cell = null;
        for (int i = 0; i < a.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(a[i]);
        }

        //处理日期格式
        //修饰表格的样式的对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //修饰日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日 HH时mm分ss秒"));

        //向表格行插入数据
        List<User> list = userDao.selectAll();
        for (int i = 0; i < list.size(); i++) {
            //从第几行插入数据
            row = sheet.createRow(i + 1);
            //向每一行的单元格中依次添加数据
            row.createCell(0).setCellValue(list.get(i).getId());
            row.createCell(1).setCellValue(list.get(i).getPhone());
            row.createCell(2).setCellValue(list.get(i).getPassword());
            row.createCell(3).setCellValue(list.get(i).getSex());

            cell = row.createCell(4);
            Date date = list.get(i).getCreateDate();
            cell.setCellValue(date);
            cell.setCellStyle(cellStyle);
        }




        //输出此表格
        workbook.write(new File("C:\\Users\\lenovo\\Desktop\\新建文件夹 (2)\\用户表.xls"));

    }
}
