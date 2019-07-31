package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.ChildrenJson;
import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import com.hearkensummertask.hearkensummertask.dao.PrivateFilesDao;
import com.hearkensummertask.hearkensummertask.dao.PublicFilesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetFiles {
    @Autowired
    PublicFilesDao publicFilesDao;
    @Autowired
    PrivateFilesDao privateFilesDao;

    public List<ChildrenJson> getPublicFiles() {

        List<PublicFiles> publiclist = publicFilesDao.findAll();
        List<ChildrenJson> list = new ArrayList<>();
        int length = publiclist.size();
        for (int i = 0; i < length; i++) {
            PublicFiles files = publiclist.get(i);

            String handler = "<span shiro:hasPermission=\""+files.getOwner()+"\" style=\"display: flex\"><input type=\"button\" id=\""+files.getFileid()+"\" value=\"重命名\" onclick=\"rePubName(this)\">&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"pubdownload?pubid=" + files.getFileid() + "\">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"deletepublicfile?fid="+files.getFileid()+"\">删除</a></span>";
            list.add(new ChildrenJson(files.getFileid(), files.getFilename(), "add", files.getOwner(), files.getCreatetime(), files.getUpdatetime(), files.getLastupdateuserid(), handler));
        }
        return list;
    }


    public List<ChildrenJson> getPrivateFiles(String userid) {

//        List<PrivateFiles> privateFiles = privateFilesDao.findAll();
        List<PrivateFiles> privateFiles = privateFilesDao.findAllByOwner(userid);
        System.out.println(privateFiles.toString());
        List<ChildrenJson> list = new ArrayList<>();
        int length = privateFiles.size();
        for (int i = 0; i < length; i++) {
            PrivateFiles files = privateFiles.get(i);
            String handler = "<spanshiro:hasPermission=\""+files.getOwner()+ "\"style=\"display: flex\"><input type=\"button\" id=\""+files.getFileid()+"\"value=\"重命名\" onclick=\"rePriName(this)\">&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"pridownload?priid=" + files.getFileid() + "\">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"deleteprivatefile?fid="+files.getFileid()+"\">删除</a></span>";
            list.add(new ChildrenJson(files.getFileid(), files.getFilename(), "add", files.getOwner(), files.getCreatetime(), files.getUpdatetime(), files.getLastupdateuserid(), handler));

//            list.add(new ChildrenJson(files.getFileid(), files.getFilename(), "add", files.getOwner(), files.getCreatetime(), files.getUpdatetime(), files.getOwner(), "    <span style=\"display: flex\"><a href=\"http://www.baidu.com\">重命名</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\">下载</a></span>"));
        }
        return list;
    }

    public PublicFiles getPublicFilePath(Integer id) {
        return publicFilesDao.findByFileid(id);

    }

    public PrivateFiles getPrivateFilePath(Integer id) {
    return privateFilesDao.findByFileid(id);

    }



}
