package com.zsc.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cmfz_user")
public class User implements Serializable {
    @Id
    private String id;
    private String phone;
    private String password;
    private String salt;
    private String dharma;
    private String name;
    private String sex;
    private String province;
    private String city;
    private String headImg;
    private String sign;
    private String status;
    private String role;
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")     //json响应字符串格式   
    @DateTimeFormat(pattern = "yyyy-MM-dd")//转换数据库日期格式
    private Date createDate;
    @JSONField(format = "yyyy-MM-dd: HH:mm:ss")     //json响应字符串格式   
    @DateTimeFormat(pattern = "yyyy-MM-dd")//转换数据库日期格式
    private Date lastUpdateDate;
    private String guruId;




}
