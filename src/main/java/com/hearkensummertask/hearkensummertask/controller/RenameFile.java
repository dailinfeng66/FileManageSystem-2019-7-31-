package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.RenameFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RenameFile {
    @Autowired
    RenameFileService renameFileService;
    @ResponseBody
    @RequestMapping("/renamePubFile")
    public String renamePubFile(HttpServletResponse response, HttpServletRequest request) {

        Integer id = Integer.valueOf(request.getParameter("fileid"));
        String fileName = request.getParameter("filename");

        renameFileService.renamePublicFile(fileName,id, (String) request.getSession().getAttribute("userid"));

        return "操作成功!";
    }


    @ResponseBody
    @RequestMapping("/renamepriFile")
    public String renamepriFile(HttpServletResponse response, HttpServletRequest request) {

        Integer id = Integer.valueOf(request.getParameter("fileid"));
        String fileName = request.getParameter("filename");

        renameFileService.renamePrivateFile(fileName,id);

        return "操作成功!";
    }


}
