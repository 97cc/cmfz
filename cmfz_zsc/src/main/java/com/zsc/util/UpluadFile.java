package com.zsc.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpluadFile {




    /**
     * 文件上传
     * @param name     文件夹名
     * @param multipartFile  文件
     * @param request
     */
    public static Map<String,String> UpluadFile(String name, MultipartFile multipartFile, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        try {
            //动态获取项目名及文件地址
            String realPath = request.getSession().getServletContext().getRealPath("/");
            //保存文件的地址
            File file = new File(realPath, name);
            if (!file.exists()) {
                file.mkdir();
            }
            //获取文件的原始名字
            String originalFilename = multipartFile.getOriginalFilename();
            map.put("originalFilename",originalFilename);

            //获取原始名字的后缀
            String extension = FilenameUtils.getExtension(originalFilename);
            map.put("extension",extension);

            //生成文件的新名字
            String  fileName = UUID.randomUUID().toString() + "." + extension;
            map.put("fileName",fileName);

            //获取文件的大小
            BigDecimal size = new BigDecimal(multipartFile.getSize());
            BigDecimal mod = new BigDecimal(1024);
            BigDecimal bigDecimal = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);
            String fileSize = bigDecimal + "MB";
            map.put("fileSize",fileSize);

            //保存文件
            multipartFile.transferTo(new File(file.getPath(), fileName));


            //获取文件时长
            Encoder encoder = new Encoder();
            long duration = encoder.getInfo(new File(file.getPath(), fileName)).getDuration();
            String time = duration / 1000 / 60 + "分" + duration / 1000 % 60 + "秒";
            System.out.println(time+"时间");
            map.put("time",time);


            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error","上传错误");
            return map;
        }

    }
}
