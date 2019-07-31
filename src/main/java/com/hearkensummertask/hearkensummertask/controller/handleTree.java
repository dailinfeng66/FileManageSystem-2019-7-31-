package com.hearkensummertask.hearkensummertask.controller;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hearkensummertask.hearkensummertask.bean.ChildrenJson;
import com.hearkensummertask.hearkensummertask.service.GetFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class handleTree {
    @Autowired
    GetFiles getFiles;


    @RequestMapping("/gettree")
    public String gettree(Map<String,Object> map,HttpServletRequest request) {

        map.put("userid",(String)request.getSession().getAttribute("userid"));
        return "treedata";
    }


    @ResponseBody
    @RequestMapping("treedata")
    public Object treedata2(HttpServletRequest request) {

        String userid = (String) request.getSession().getAttribute("userid");
        System.out.println(userid);

        String begin = "[ { \"id\": 1, \"text\": \"文档\", \"children\": [ { \"id\": 2, \"text\": \"工作文档\", \"children\": ";
        String end = "} ] } ] ";
        String privatefile = "},{ \"id\": 3, \"text\": \"个人文档\", \"children\":  ";


        List<ChildrenJson> publiclist = getFiles.getPublicFiles();
        List<ChildrenJson> privatelist = getFiles.getPrivateFiles(userid);
        String list = JSONArray.toJSON(publiclist).toString();
        String list2 = JSONArray.toJSON(privatelist).toString();

        String data = begin + list + privatefile + list2 + end;
        return JSONObject.toJSON(data);
    }
}

