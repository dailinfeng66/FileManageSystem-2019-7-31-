package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.PrivateFilesDao;
import com.hearkensummertask.hearkensummertask.dao.PublicFilesDao;
import com.hearkensummertask.hearkensummertask.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UDfile {
    @Autowired
    PrivateFilesDao privateFilesDao;
    @Autowired
    PublicFilesDao publicFilesDao;
    @Autowired
    UserDao userDao;
    @Autowired
    GetNowTime getNowTime;

    public boolean UploadPublicFile(String filepath, MultipartFile file,HttpServletRequest request){
        boolean result = false;
        String fileName = file.getOriginalFilename();
        String uuidname = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        String FileTotalPath = filepath+uuidname;

        File dest = new File(FileTotalPath);
        String userid = (String) request.getSession().getAttribute("userid");
        User user = userDao.findByUserid(userid);

        String nowTime = getNowTime.getTime();
        PublicFiles publicFiles = new PublicFiles(fileName,FileTotalPath,nowTime,nowTime,user.getUserid(),userid);
        publicFilesDao.save(publicFiles);
        try {
            file.transferTo(dest);
            result = true;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }


    public boolean UploadPrivateFile(String filepath, MultipartFile file, HttpServletRequest request){
        boolean result = false;
        String fileName = file.getOriginalFilename();
        String uuidname = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        String FileTotalPath = filepath+uuidname;
        String userid = (String) request.getSession().getAttribute("userid");
        User user = userDao.findByUserid(userid);

        String nowTime = getNowTime.getTime();
        PrivateFiles privateFiles = new PrivateFiles(fileName,FileTotalPath,nowTime,nowTime,userid,userid);
        privateFilesDao.save(privateFiles);
        File dest = new File(FileTotalPath);
        try {
            file.transferTo(dest);
            result = true;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }
}
