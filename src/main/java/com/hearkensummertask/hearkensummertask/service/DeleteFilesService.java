package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import com.hearkensummertask.hearkensummertask.dao.PrivateFilesDao;
import com.hearkensummertask.hearkensummertask.dao.PublicFilesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DeleteFilesService {
    @Autowired
    PublicFilesDao publicFilesDao;
    @Autowired
    PrivateFilesDao privateFilesDao;

    public void deletePublicFiles(Integer id){
        PublicFiles publicFiles = publicFilesDao.getOne(id);
        File file = new File(publicFiles.getFilepath());
        file.delete();
        publicFilesDao.deleteById(id);
    }
    public void deletePrivateFiles(Integer id){
        PrivateFiles privateFiles = privateFilesDao.getOne(id);
        File file = new File(privateFiles.getFilepath());
        file.delete();
        privateFilesDao.deleteById(id);
    }


}
