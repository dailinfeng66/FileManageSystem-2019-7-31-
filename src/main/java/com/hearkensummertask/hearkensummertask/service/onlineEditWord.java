package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.dao.PrivateFilesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Service
public class onlineEditWord {

    @Autowired
    PrivateFilesDao privateFilesDao;
    @Autowired
    GetNowTime getNowTime;

    public Boolean saveDocs(String content) {
        Boolean flag = true;
/**
 * 存文件到本地文件夹
 * 然后存到数据库信息
 *
 *      BufferedWriter out = new BufferedWriter(new FileWriter("D:\\文档\\个人文档\\"));
 *         out.write(content);
 *         out.close();
 */
        BufferedWriter out = null;
        try {
            String uuid = UUID.randomUUID().toString();
            String filepath = "D:\\文档\\个人文档\\" + uuid + ".doc";
            out = new BufferedWriter(new FileWriter(filepath));
            out.write(content);
            out.close();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String owner = (String) request.getSession().getAttribute("userid");
            privateFilesDao.save(new PrivateFiles(getNowTime.getTime() + "新建文件", filepath, getNowTime.getTime(), getNowTime.getTime(), owner, owner));
            //存数据库结束

        } catch (IOException e) {
            System.out.println("文件保存错误");
            flag = false;
        }
        return flag;
    }

    /**
     * 点击保存到本地   之后的一种通过服务器传输的方案
     * 问题: 前端是通过ajax post的方式  传回去的数据无法显示下载
     *        前端要是用post  无法提交数据
     *
     *
     *        初步想到的解决办法 :
     *                  保存到本地再弄一个form  点击之后在form提交之前先ajax传回去   点击form请求之后在返回doc文件
     *
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    public Boolean saveFilesInLocal(String content) throws UnsupportedEncodingException {
        Boolean flag = true;
        String fileName = "新建文件.doc";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        BufferedWriter out = null;
        String filepath = "";
        try {
            String uuid = UUID.randomUUID().toString();
            filepath = "D:\\文档\\临时文档\\" + uuid + ".doc";
            out = new BufferedWriter(new FileWriter(filepath));
            out.write(content);
            out.close();
        } catch (IOException e) {
            System.out.println("文件保存错误");
            flag = false;
        }


        File file = new File(filepath);
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

        file.delete();
        return flag;
    }

}
