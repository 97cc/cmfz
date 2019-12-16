package com.zsc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cmfz_slideshow")
public class Slideshow implements Serializable {
    @Id
    private String id;
    private String title;
    private String cover;
    private Integer status;
    private Date createDate;  //上传时间
    private Date lastUpdateDate;  //修改时间
}
