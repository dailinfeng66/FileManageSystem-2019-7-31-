package com.hearkensummertask.hearkensummertask;


import com.alibaba.fastjson.JSONObject;
import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.PublicFilesDao;
import com.hearkensummertask.hearkensummertask.dao.UserDao;

import com.hearkensummertask.hearkensummertask.service.SendSms;
import com.hearkensummertask.hearkensummertask.service.WebSocket;
import com.hearkensummertask.hearkensummertask.utils.SendQQMailUtil;
import org.checkerframework.checker.units.qual.A;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HearkensummertaskApplicationTests {
    @Autowired
    UserDao userDao;
    @Autowired
    PublicFilesDao publicFilesDao;

//    @Test
//    public void contextLoads() {
//        User user = new User("123", "123123", "张喜", "aaaa");
//        userDao.save(user);
//        System.out.println("\n\n\n\n\n\n\n" + user + "\n\n\n\n\n\n\n\n\n");
//    }


    @Test
    public void renamePublicFile() {
        String oldFilePath = "D:\\文档\\个人文档\\30ed0329f8282e2f41bd40e0de9a2957.jpg";
        String lastname = oldFilePath.substring(oldFilePath.lastIndexOf("."));
        String newFileName = "银河星空图" + lastname;
        File oldfile = new File(oldFilePath);

        String newFilepath = oldfile.getParent() + File.separator + newFileName;
        File newFile = new File(newFilepath);
        oldfile.renameTo(newFile);

    }
      @Autowired
    WebSocket webSocket;
@Autowired
    SendSms sendSms;
    @Test
    public void teqwe() throws IOException {
        String code = "";
//       sendSms.sendYzm();
    }


    @Test
    public  void convertDocxToHtml() throws Exception{
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        wordMLPackage.getMainDocumentPart().addParagraphOfText("<html><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/><h1 style=\"font-size: 32px; font-weight: bold; border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: left; margin: 0px 0px 10px;\">asdasdasdasd<br/></h1><p><br/></p></html>");
//        wordMLPackage.getMainDocumentPart().addParagraphOfText("<h6>hello world</h6>");
//        wordMLPackage.getMainDocumentPart().addParagraphOfText("张喜是猪!");
//         Tbl tbl = new docxUtils().createTable(wordMLPackage,3,3);
//        wordMLPackage.getMainDocumentPart().addObject(tbl);
        wordMLPackage.save(new java.io.File("D:\\写入测试.docx"));


    }

}
