package com.zsc.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 章节
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cmfz_chapter")
public class Chapter implements Serializable {
    @Id
    @Excel(name = "编号",width = 30)
    private String id;
    @Excel(name = "上师编号",width = 30)
    private String albumId;
    @Excel(name = "标题",width = 30)
    private String title;
    @Excel(name = "音频大小",width = 30)
    private String size;
    @Excel(name = "音频时长",width = 30)
    private String duration;
    @Excel(name = "音频",width = 30)
    private String audioPath;
    @Excel(name = "状态",width = 30)
    private String status;

    @Excel(name = "创建时间",width = 30,format= "yyyy-MM-dd HH-mm-ss")
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")     //json响应字符串格式   
    @DateTimeFormat(pattern = "yyyy-MM-dd")//转换数据库日期格式
    private Date createDate;

    @Excel(name = "修改时间",width = 30,format= "yyyy-MM-dd HH-mm-ss")
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")     //json响应字符串格式   
    @DateTimeFormat(pattern = "yyyy-MM-dd")//转换数据库日期格式
    private Date lastUpdateDate;
}
