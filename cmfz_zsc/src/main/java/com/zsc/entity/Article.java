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
 * 文章
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cmfz_article")
public class Article implements Serializable {
    @Id
    private String id;
    private String guruId;
    private String title;
    private String author;
    private String headImg;
    private Date publishDate;
    private String content;
}
