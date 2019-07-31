package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.onlineEditWord;
import org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class Edittext {



    @RequestMapping("/edit2")
    public String Toedit2(){
        return "editer";
//        templates/
    }

    @Autowired
    onlineEditWord onlineEditWord;
    @ResponseBody
    @RequestMapping("/editarr")
    public String getarr(HttpServletRequest request)    {
        String content = request.getParameter("content");
        /**
         * 得到内容之后把他转化成为一个HTML文件?
         *
         */
        content = "<html><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>"+content+"</html>";

    /**
     * 保存到本地文件夹
     */
        Boolean result = onlineEditWord.saveDocs(content);
        if(result){
            return "success";
        }else {
            return "发生错误";
        }
    }



    @RequestMapping("/saveInLocalhost")
    public void saveInLocalhost(){

    }

}
