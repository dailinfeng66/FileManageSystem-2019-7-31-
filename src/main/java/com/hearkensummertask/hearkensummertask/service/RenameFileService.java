package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import com.hearkensummertask.hearkensummertask.dao.PrivateFilesDao;
import com.hearkensummertask.hearkensummertask.dao.PublicFilesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenameFileService {
    @Autowired
    PublicFilesDao publicFilesDao;
    @Autowired
    PrivateFilesDao privateFilesDao;
    @Autowired
    GetNowTime getNowTime;

    public void renamePublicFile(String newFileName, Integer id,String lastModify) {
        PublicFiles publicFiles = publicFilesDao.getOne(id);

//        String oldFilePath=publicFiles.getFilepath();
//        String lastname = oldFilePath.substring(oldFilePath.lastIndexOf("."));
//
//         newFileName=newFileName+lastname;
//        File oldfile = new File(oldFilePath);
//
//        String newFilepath = oldfile.getParent() + File.separator + newFileName;
//        File newFile = new File(newFilepath);
//        oldfile.renameTo(newFile);
//
//        publicFiles.setFilepath(newFilepath);
        publicFiles.setLastupdateuserid(lastModify);
        publicFiles.setUpdatetime(getNowTime.getTime());
        publicFiles.setFilename(newFileName);
        publicFilesDao.save(publicFiles);

    }


    public void renamePrivateFile(String newFileName, Integer id) {
        PrivateFiles privateFiles = privateFilesDao.getOne(id);

//        String oldFilePath=privateFiles.getFilepath();
//        String lastname = oldFilePath.substring(oldFilePath.lastIndexOf("."));
//        newFileName=newFileName+lastname;
//        File oldfile = new File(oldFilePath);
//
//        String newFilepath = oldfile.getParent() + File.separator + newFileName;
//        File newFile = new File(newFilepath);
//        oldfile.renameTo(newFile);


//        privateFiles.setFilepath(newFilepath);
        privateFiles.setUpdatetime(getNowTime.getTime());
        privateFiles.setFilename(newFileName);
        privateFilesDao.save(privateFiles);

    }
}
