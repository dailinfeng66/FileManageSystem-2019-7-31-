package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.GetFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileDownLoad {
    @Autowired
    GetFiles getFiles;

    //文件下载相关代码
    @RequestMapping("/pubdownload")
    public String downloadFile(@RequestParam("pubid") Integer pubid, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        /**
         * 这个filename应该是从数据库查出来的
         */
        String realPath = getFiles.getPublicFilePath(pubid).getFilepath();//
        String fileName = getFiles.getPublicFilePath(pubid).getFilename() + realPath.substring(realPath.lastIndexOf("."));

        if (fileName != null) {
            //设置文件路径
//            String realPath = "D:\\文档\\公共文档";
            File file = new File(realPath);
            if (file.exists()) {


                //浏览器设置
                String userAgent = request.getHeader("User-Agent");
                if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                    //IE浏览器处理
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                } else {
                    // 非IE浏览器的处理：
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                }
// 设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
// 设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("application/octet-stream;charset=utf-8");


                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


    //文件下载相关代码
    @RequestMapping("/pridownload")
    public String pridownload(@RequestParam("priid") Integer priid, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        /**
         * 这个filename应该是从数据库查出来的
         */

        String realPath = getFiles.getPrivateFilePath(priid).getFilepath();//

        String fileName = getFiles.getPrivateFilePath(priid).getFilename() + realPath.substring(realPath.lastIndexOf("."));//

        if (realPath != null) {
            //设置文件路径
//            String realPath = "D:\\文档\\个人文档";
            File file = new File(realPath);
            if (file.exists()) {
                //浏览器设置
                String userAgent = request.getHeader("User-Agent");
                if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                    //IE浏览器处理
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                } else {
                    // 非IE浏览器的处理：
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                }
// 设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
// 设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("application/octet-stream;charset=utf-8");


                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
