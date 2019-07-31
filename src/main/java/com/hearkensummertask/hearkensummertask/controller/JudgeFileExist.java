package com.hearkensummertask.hearkensummertask.controller;

import com.alibaba.fastjson.JSONObject;
import com.hearkensummertask.hearkensummertask.service.JudgeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class JudgeFileExist {
    @Autowired
    JudgeFile judgeFile;
    @RequestMapping("/judgePubfileExist")
    public Boolean judgePubfileExist(HttpServletRequest request, HttpServletResponse response) {
        String filename = request.getParameter("filename");
        boolean result =  judgeFile.judgePubFile(filename);
        return result;
    }


    @RequestMapping("/judgePrivateExist")
    public Boolean judgePrivateExist(HttpServletRequest request, HttpServletResponse response) {
        String filename = request.getParameter("filename");
        boolean result =  judgeFile.judgePrivateFile(filename);
        return result;
    }

}
