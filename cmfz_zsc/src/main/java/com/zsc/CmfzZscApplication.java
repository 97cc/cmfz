package com.zsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.zsc.dao")
@SpringBootApplication
public class CmfzZscApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzZscApplication.class, args);
    }

}
