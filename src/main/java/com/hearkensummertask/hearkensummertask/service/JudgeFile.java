package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import com.hearkensummertask.hearkensummertask.dao.PrivateFilesDao;
import com.hearkensummertask.hearkensummertask.dao.PublicFilesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeFile {
    @Autowired
    PublicFilesDao publicFilesDao;
    @Autowired
    PrivateFilesDao privateFilesDao;

    public boolean judgePubFile(String FileName) {
        boolean result = false;

        PublicFiles publicFiles = publicFilesDao.findByFilename(FileName);
        if (publicFiles == null) {  //等于空 就是在数据库没有查到  那就是   文件名可用
            result = true;
        }
        return result;

    }


    public boolean judgePrivateFile(String FileName) {
        boolean result = false;

      PrivateFiles privateFiles = privateFilesDao.findByFilename(FileName);
        if (privateFiles == null) {  //等于空 就是在数据库没有查到  那就是   文件名可用
            result = true;
        }
        return result;

    }


}
