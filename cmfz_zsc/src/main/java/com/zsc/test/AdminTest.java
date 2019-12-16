package com.zsc.test;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zsc.CmfzZscApplication;
import com.zsc.dao.AdminDao;
import com.zsc.dao.AlbumDao;
import com.zsc.dao.ChapterDao;
import com.zsc.dao.UserDao;
import com.zsc.dto.UserCount;
import com.zsc.entity.Admin;
import com.zsc.entity.Chapter;
import com.zsc.service.AdminService;
import com.zsc.service.ChapterService;
import com.zsc.util.Md5UUIDSaltUtil;
import com.zsc.util.SMSVerification;
import io.goeasy.GoEasy;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = CmfzZscApplication.class)
@RunWith(SpringRunner.class)
public class AdminTest {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminDao adminDao;

    @Autowired
    ChapterService chapterService;
    @Autowired
    AlbumDao albumDao;
    @Autowired
    HttpServletRequest request;

    @Autowired
    ChapterDao chapterDao;

    @Autowired
    UserDao userDao;
    @Test
    public void login(){
        List<Chapter> list = chapterService.queryPage(1, 1, "123");
        System.out.println(list.toString());


    }


    @Test
    public void qqq() {
        String salt = Md5UUIDSaltUtil.getSalt();
        System.out.println(salt+"盐值");
        String md5Code = Md5UUIDSaltUtil.createMd5Code("123"+"123"+"123");
        System.out.println(md5Code+"加密后");
        //String uuid = Md5UUIDSaltUtil.getUUID();
       // System.out.println(uuid);
        boolean b = Md5UUIDSaltUtil.checkPassword("123",md5Code);
        System.out.println(b+"jiegu");
//f5bb0c8de146c67b44babbf4e6584cc0
    }


    @Test
    public void name() {
        Admin admin = new Admin();
        admin.setPassword("f5bb0c8de146c67b44babbf4e6584cc0");
        admin.setId("123");
        adminDao.updateByPrimaryKeySelective(admin);
    }


   /* @Test
    public void testEasy() {
        List<Album> albums = albumDao.selectAll();//获取专辑集合
        //List<Chapter> chapters = chapterDao.selectAll();//获取专辑章节集合
        Chapter chapter = new Chapter();

        for (Album album : albums) {
            String id = album.getId();
            chapter.setAlbumId(id);
            List<Chapter> chapters = chapterDao.select(chapter);
            String companyLogo = album.getCover();
            String realPath = request.getSession().getServletContext().getRealPath("/album/"+companyLogo);
            album.setCover(realPath);
            album.setChapters(chapters);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(

                new ExportParams("专辑信息表","专辑"), Album.class, albums);
        try {
            workbook.write(new FileOutputStream("C:/Users/lenovo/Desktop/新建文件夹 (2)/"+"album.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/


    @Test
    public void goeasy() {

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-5c87e20b04854bd590eb273711322ba1");
        goEasy.publish("my_cmfz","来啦，老弟！！！");
    }


    @Test
    public void naafdasme() {
      //  String sex = "'"+"男"+"'";
        List<UserCount> list = userDao.queryUserProvinceCount("男");
        List<UserCount> list2 = userDao.queryUserProvinceCount("女");
        System.out.println("|||"+list);
        for (UserCount userCount : list) {
            System.out.println(userCount);
        }
        for (UserCount userCount : list2) {

            System.out.println("||"+userCount);
        }

    }


    @Test

    public void dsa() {
        String s = SMSVerification.SMSVerification("18697790305", "123456");
        System.out.println("状态"+s);
    }
}

