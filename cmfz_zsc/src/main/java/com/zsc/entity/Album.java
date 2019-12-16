package com.zsc.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
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
import java.util.List;

/**
 * 专辑表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cmfz_album")
public class Album implements Serializable {

    @Id
    @Excel(name = "编号",width = 30,needMerge = true)
    private String id;
    @Excel(name = "标题",width = 30,needMerge = true)
    private String title;
    @Excel(name = "图片", type = 2 ,width = 30 , height = 20,needMerge = true)
    private String cover;
    @Excel(name = "集数",needMerge = true)
    private String amount;
    @Excel(name = "平分",needMerge = true)
    private Double score;
    @Excel(name = "作者",width = 30,needMerge = true)
    private String author;
    @Excel(name = "播音",width = 30,needMerge = true)
    private String broadcaster;
    @Excel(name = "内容",width = 30,needMerge = true)
    private String content;
    @Excel(name = "状态",needMerge = true)
    private String status;

    @Excel(name = "发布时间",width = 30,format = "yyyy-MM-dd HH-mm-ss",needMerge = true)
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")     //json响应字符串格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")          //转换数据库日期格式
    private Date publishDate;

    @Excel(name = "添加时间",width = 30,format = "yyyy-MM-dd HH-mm-ss",needMerge = true)
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Excel(name = "修改时间",width = 30,format = "yyyy-MM-dd HH-mm-ss",needMerge = true)
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastUpdateDate;

    @ExcelCollection(name ="专辑章节信息")
    private List<Chapter> chapters;






}
