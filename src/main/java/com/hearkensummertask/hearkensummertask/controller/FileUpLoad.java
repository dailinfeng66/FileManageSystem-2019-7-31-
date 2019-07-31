package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.UDfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUpLoad {
    @Autowired
    UDfile uDfile;


@RequestMapping("/fileUp")
public String fileUpLoad(){
    return "fileUpLoad";
}

    @ResponseBody
    @RequestMapping("/uploadPublic")
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return "上传失败!";
        }

        String filepath = "D:\\文档\\公共文档\\";
        boolean result = uDfile.UploadPublicFile(filepath,file,request);
        if(result){
            return "上传成功!";
        }
        return "上传失败!";
    }
    @ResponseBody
    @RequestMapping("/uploadPrivate")
    public String uploadPrivate(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        if(file.isEmpty()){
            return "上传失败!";
        }

        String filepath = "D:\\文档\\个人文档\\";
        boolean result = uDfile.UploadPrivateFile(filepath,file,request);
        if(result){
            return "上传成功!";
        }
        return "上传失败!";
    }

}
