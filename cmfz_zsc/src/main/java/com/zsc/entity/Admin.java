package com.zsc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name ="cmfz_admin")
public class Admin implements Serializable {
    @Id
    private String id;
    private String name;
    private String password;
    private String nikcName;
    private String salt;




}
